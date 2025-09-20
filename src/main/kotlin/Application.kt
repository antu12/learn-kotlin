package com.antu

import com.antu.Plugins.configureHTTP
import com.antu.Plugins.configureResources
import com.antu.Plugins.configureRouting
import com.antu.Plugins.configureSerialization
import com.antu.Plugins.configureStatusPages

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureResources()
    configureHTTP()
    configureRouting()
    configureSerialization()
    configureStatusPages()
}
