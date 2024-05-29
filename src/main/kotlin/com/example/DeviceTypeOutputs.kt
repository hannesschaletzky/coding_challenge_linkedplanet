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

private object DeviceTypeOutputs : Table<Nothing>("device_type_outputs") {
    val id = int("id").primaryKey()
    val device_type_name = varchar("device_type_name")
    val output_device_type_name = varchar("output_device_type_name")
}

@Serializable
private class DeviceTypeOutput(val id: Int, val device_type_name: String, val output_device_type_name: String)

fun Application.configureDeviceTypeOutputs() {
    routing {
        get("/device_type_outputs") {
            val deviceTypeOutputs = mutableListOf<DeviceTypeOutput>()
            database.from(DeviceTypeOutputs).select().forEach { row ->
                val id = row[DeviceTypeOutputs.id]
                val device_type_name = row[DeviceTypeOutputs.device_type_name]
                val output_device_type_name = row[DeviceTypeOutputs.output_device_type_name]
                if (id != null && device_type_name != null && output_device_type_name != null) {
                    deviceTypeOutputs.add(DeviceTypeOutput(id, device_type_name, output_device_type_name))
                } else {
                    call.respond("Invalid data received from database")
                    return@forEach
                }
            }
            call.respond(deviceTypeOutputs)
        }
    }
}
