package com.antu.features.account

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.accountRoutes(){
    route("account"){
        route("user"){
            get(""){
                call.respondText { "get all users" }
            }
            get("{id}"){

            }
            post("") {

            }
            patch("{id}"){

            }
        }
        route("auth"){
            post("login"){

            }
            post("signup"){

            }
        }
    }
}