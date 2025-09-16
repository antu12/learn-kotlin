package com.antu.features.product

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable

fun Application.productRoutes() {
    routing {
        get("product") {
            call.respondText { "Show all Products!" }
        }
        // Serializable JSON
        post("product"){
            val product = call.receiveNullable<Product>() ?: return@post call.respond(HttpStatusCode.BadRequest)
            call.respond(product)
        }
    }
}

@Serializable
data class Product (
    val name: String,
    val category: String,
    val price: Double
)