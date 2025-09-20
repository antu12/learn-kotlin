package com.antu.features.product

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.*
import io.ktor.server.application.Application
import io.ktor.server.request.receiveMultipart
import io.ktor.server.request.receiveNullable
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import io.ktor.server.routing.routing
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.copyAndClose
import kotlinx.serialization.Serializable
import java.io.File

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

        post("checkout") {
            val formData = call.receiveParameters()
            val productId = formData["productId"]
            val quantity = formData["quantity"]
            call.respondText { "checkout success, product id is: $productId & quantity: $quantity" }
        }

        post("multipartData") {
            val data = call.receiveMultipart(formFieldLimit = 1024*1024*50)
            val fields = mutableMapOf<String, MutableList<String>>()
            data.forEachPart { part ->
                when(part) {
                    is PartData.FormItem -> {
                        val key = part.name ?: return@forEachPart
                        fields.getOrPut(key) { mutableListOf() }.add(part.value)
                        part.dispose()
                    }
                    is PartData.FileItem -> {
                        val key = part.name ?: return@forEachPart
                        val fileName = part.originalFileName ?: return@forEachPart
                        fields.getOrPut(key) { mutableListOf() }.add(fileName)
                        val file = File("uploads/${fileName}").apply {
                            parentFile?.mkdirs()
                        }
                        part.provider().copyAndClose(file.writeChannel())
                        part.dispose()
                    }
                    else -> {

                    }
                }
            }
            call.respondText { "Form files: $fields" }
        }
        post ("add_product"){
            throw Exception("Database initialization failed")
        }

        post("products") {
            call.respondText("", status = HttpStatusCode.Unauthorized)
        }

    }
}

@Serializable
data class Product (
    val name: String,
    val category: String,
    val price: Double
)