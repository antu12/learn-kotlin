package com.antu.Plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText("500 ${cause.message}", status = HttpStatusCode.InternalServerError)
        }
        status(HttpStatusCode.Unauthorized){call, code ->
            call.respondText ( "401: You are not authorized to access this resource" )
        }
        statusFile(HttpStatusCode.BadRequest, HttpStatusCode.Unauthorized, filePattern = "errors/error#.html")
    }
}