package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.ktorm.dsl.forEach
import org.ktorm.dsl.from
import org.ktorm.dsl.select
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
            val devices = mutableListOf<Device>()
            database.from(Devices).select().forEach { row ->
                val id = row[Devices.id]
                val name = row[Devices.name]
                val deviceTypeName = row[Devices.device_type_name]
                if (id != null && name != null && deviceTypeName != null) {
                    devices.add(Device(id, name, deviceTypeName))
                } else {
                    call.respond("Invalid data received from database")
                    return@forEach
                }
            }
            call.respond(devices)
        }
    }
}
