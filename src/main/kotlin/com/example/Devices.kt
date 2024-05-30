package com.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.ktorm.dsl.*
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

private object Devices : Table<Nothing>("devices") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val device_type_name = varchar("device_type_name")
}

@Serializable
private class Device(val id: Int, val name: String, val device_type_name: String)

fun Application.configureDevices() {
    routing {
        get("/devices") {
            val devices = database.from(Devices)
                .select()
                .mapNotNull { row ->
                    val id = row[Devices.id]
                    val name = row[Devices.name]
                    val deviceTypeName = row[Devices.device_type_name]
                    if (id != null && name != null && deviceTypeName != null) {
                        Device(id, name, deviceTypeName)
                    } else {
                        null
                    }
                }
            if (devices.isNotEmpty()) {
                call.respond(devices)
            } else {
                call.respond(HttpStatusCode.NotFound, "No devices found")
            }
        }
        get("/devices/{device_name}") {
            val deviceName = call.parameters["device_name"]
            if (deviceName != null) {
                val deviceRow = database
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

                if (deviceRow != null) {
                    call.respond(deviceRow)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Device not found")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Missing or invalid device name")
            }
        }
    }
}
