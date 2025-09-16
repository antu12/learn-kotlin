package com.antu.Plugins

import com.antu.features.account.accountRoutes
import com.antu.features.blogs.blogRoutes
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.request.receiveChannel
import io.ktor.server.request.receiveStream
import io.ktor.server.request.receiveText
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.copyAndClose
import io.ktor.utils.io.core.readText
import io.ktor.utils.io.readRemaining
import java.io.File
import java.io.FileOutputStream


fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get(Regex(".+/test")){
            call.respondText { "Test API is called!" }
        }
        get(Regex("/api/(?<apiVersion>v[1-3])/users")) {
            val v = call.pathParameters["apiVersion"]
            call.respondText { "Api version is $v" }
        }

        post("greet") {
            val name = call.receiveText()
            call.respondText { "Hello $name" }
        }

        post("channel"){
            val ch = call.receiveChannel()
            val text = ch.readRemaining().readText()
            call.respondText { text }
        }

        post("upload"){
            val file = File("uploads/sample.jpg").apply {
                parentFile?.mkdirs()
            }
//            byte handle
//            val byte = call.receive<ByteArray>()
//            file.writeBytes(byte)

//            Stream handle
//            val stream = call.receiveStream()
//            FileOutputStream(file).use { outputStream ->
//                stream.copyTo(outputStream, bufferSize = 16*1024)
//            }

//            channel handle
            val ch = call.receiveChannel()
            ch.copyAndClose(file.writeChannel())

            call.respondText { "File uploaded!" }
        }

        blogRoutes()
        accountRoutes()
    }
}
