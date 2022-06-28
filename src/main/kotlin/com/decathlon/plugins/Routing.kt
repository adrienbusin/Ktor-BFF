package com.decathlon.plugins

import com.decathlon.apiKey.ApiKeyUtils
import com.decathlon.apiKey.ErrorResponse
import com.decathlon.apiKey.models.info
import com.decathlon.apiKey.routes.deleteApiKeyRouting
import com.decathlon.apiKey.routes.getApiKeyRouting
import com.decathlon.apiKey.routes.postApiKeyRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        getApiKeyRouting()
        postApiKeyRouting()
        deleteApiKeyRouting()

        get {
            call.respond(message = "coucou")
        }
    }
}
