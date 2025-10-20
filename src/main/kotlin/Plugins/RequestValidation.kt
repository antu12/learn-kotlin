package com.antu.Plugins

import com.antu.features.product.Product
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun Application.configureRequestValidation() {
    install(RequestValidation){
        validate<String>{body ->
            if (body.isBlank()) ValidationResult.Invalid("Message cannot be empty")
            else ValidationResult.Valid
        }
        validate<Product> { body ->
            if (body.name.isBlank()) ValidationResult.Invalid("Name cannot be empty")
            else if (body.category.isBlank()) ValidationResult.Invalid("Category cannot be empty")
            else if (body.price.isNaN() || body.price <= 0) ValidationResult.Invalid("Price cannot be le 0")
            else ValidationResult.Valid
        }
    }
}