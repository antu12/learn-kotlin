package com.antu.Plugins

import com.antu.features.account.accountRoutes
import com.antu.features.blogs.blogRoutes
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.request.receiveText
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get


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

        blogRoutes()
        accountRoutes()
    }
}
