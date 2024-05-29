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

private object DeviceTypes : Table<Nothing>("device_types") {
    val id = int("id").primaryKey()
    val name = varchar("name")
}

@Serializable
private class DeviceType(val id: Int, val name: String)

fun Application.configureDeviceTypes() {
    routing {
        get("/device_types") {
            val deviceTypes = mutableListOf<DeviceType>()
            database.from(DeviceTypes).select().forEach { row ->
                val id = row[DeviceTypes.id]
                val name = row[DeviceTypes.name]
                if (id != null && name != null) {
                    deviceTypes.add(DeviceType(id, name))
                } else {
                    call.respond("Invalid data received from database")
                    return@forEach
                }
            }
            call.respond(deviceTypes)
        }
    }
}