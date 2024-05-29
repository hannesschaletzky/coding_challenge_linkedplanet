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

private object DeviceTypeInputs : Table<Nothing>("device_type_inputs") {
    val id = int("id").primaryKey()
    val device_type_name = varchar("device_type_name")
    val input_device_type_name = varchar("input_device_type_name")
}

@Serializable
private class DeviceTypeInput(val id: Int, val device_type_name: String, val input_device_type_name: String)

fun Application.configureDeviceTypeInputs() {
    routing {
        get("/device_type_inputs") {
            val deviceTypeInputs = mutableListOf<DeviceTypeInput>()
            database.from(DeviceTypeInputs).select().forEach { row ->
                val id = row[DeviceTypeInputs.id]
                val device_type_name = row[DeviceTypeInputs.device_type_name]
                val input_device_type_name = row[DeviceTypeInputs.input_device_type_name]
                if (id != null && device_type_name != null && input_device_type_name != null) {
                    deviceTypeInputs.add(DeviceTypeInput(id, device_type_name, input_device_type_name))
                } else {
                    call.respond("Invalid data received from database")
                    return@forEach
                }
            }
            call.respond(deviceTypeInputs)
        }
    }
}
