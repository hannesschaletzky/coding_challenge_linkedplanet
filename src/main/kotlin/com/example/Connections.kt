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

            val sourceDevice = retrieveDevice(sourceDeviceName)
            if (sourceDevice == null) {
                call.respond(HttpStatusCode.NotFound, "$sourceDeviceName not found")
                return@post
            }

            val targetDevice = retrieveDevice(targetDeviceName)
            if (targetDevice == null) {
                call.respond(HttpStatusCode.NotFound, "$targetDeviceName not found")
                return@post
            }

            val availableOutputs = database
                .from(DeviceTypeOutputs)
                .select()
                .where { DeviceTypeOutputs.device_type_name eq sourceDevice.device_type_name}
                .map { row ->
                    row[DeviceTypeOutputs.output_device_type_name]
                }

            if(!availableOutputs.contains(targetDevice.device_type_name)) {
                call.respond(HttpStatusCode.BadRequest, "Connection forbidden. '$sourceDeviceName' is of type '${sourceDevice.device_type_name}' and can only connect to devices of type $availableOutputs")
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
            call.respond("connection created")
        }
    }
}

private fun retrieveDevice(deviceName: String): Device? {
    return database
        .from(Devices)
        .select()
        .where { Devices.name eq deviceName }
        .map { row ->
            Device(
                id = row[Devices.id]!!,
                name = row[Devices.name]!!,
                device_type_name = row[Devices.device_type_name]!!
            )
        }
        .firstOrNull()
}