package com.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.ktorm.dsl.*
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

@Serializable
data class ConnectionRequest(
    val source_device_name: String,
    val target_device_name: String
)

object Connections : Table<Nothing>("connections") {
    val id = int("id").primaryKey()
    val source_device_name = varchar("source_device_name")
    val target_device_name = varchar("target_device_name")
}

fun Application.configureConnections() {
    routing {
        post("/connections") {
            val connectionRequest = call.receive<ConnectionRequest>()
            val sourceDeviceName = connectionRequest.source_device_name.trim()
            val targetDeviceName = connectionRequest.target_device_name.trim()
            if (sourceDeviceName.isEmpty() || targetDeviceName.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "Please provide values for source_device_name and target_device_name")
                return@post
            }

            val sourceDeviceExists = database
                .from(Devices)
                .select()
                .where { Devices.name eq sourceDeviceName }
                .totalRecords > 0

            val targetDeviceExists = database
                .from(Devices)
                .select()
                .where { Devices.name eq targetDeviceName }
                .totalRecords > 0

            if (!sourceDeviceExists) {
                call.respond(HttpStatusCode.BadRequest, "Source device does not exist")
                return@post
            }
            if (!targetDeviceExists) {
                call.respond(HttpStatusCode.BadRequest, "Target device does not exist")
                return@post
            }

            try {
                database.insert(Connections) {
                    set(it.source_device_name, connectionRequest.source_device_name)
                    set(it.target_device_name, connectionRequest.target_device_name)
                }
            } catch (e: Exception) {
                println("ERROR: $e")
                call.respond(HttpStatusCode.InternalServerError, "error occurred")
            }
            call.respond("success")
        }
    }
}