package com.example

import com.example.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseConfig.init()
    configureDeviceTypes()
    configureDevices()
    configureDeviceTypeInputs()
    configureDeviceTypeOutputs()
    configureConnections()
    configureSerialization()
    configureDatabases()
    configureMonitoring()
    configureHTTP()
    configureRouting()
}
