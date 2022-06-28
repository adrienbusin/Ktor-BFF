package com.decathlon.apiKey.routes

import com.decathlon.apiKey.ApiKeyUtils.Companion.CONTEXT
import com.decathlon.apiKey.ApiKeyUtils.Companion.ENV
import com.decathlon.apiKey.ErrorResponse
import com.decathlon.apiKey.dao.ApiKeyDao
import com.decathlon.apiKey.models.info
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getApiKeyRouting() {
    val service = ApiKeyDao()
    get("apiKeys/{env?}") {
        val env = call.parameters[ENV] ?: return@get call.respond(
            status = HttpStatusCode.BadRequest, message = ErrorResponse.BAD_REQUEST_MISSING_ENV_RESPONSE
        )

        service.getApiKeys().map { it.info(env = env) }.let {
            if (it.isNotEmpty()) {
                call.respond(message = it)
            } else {
                call.respond(status = HttpStatusCode.NotFound, message = ErrorResponse.API_KEY_NOT_FOUND_RESPONSE)
            }
        }
    }

    get("apiKeys/{env?}/{context?}") {
        val env = call.parameters[ENV] ?: return@get call.respond(
            status = HttpStatusCode.BadRequest, message = ErrorResponse.BAD_REQUEST_MISSING_ENV_RESPONSE
        )
        val context = call.parameters[CONTEXT] ?: return@get call.respond(
            status = HttpStatusCode.BadRequest, message = ErrorResponse.BAD_REQUEST_MISSING_CONTEXT_RESPONSE
        )

        val apiKey = service.getApiKeyByContext(context) ?: return@get call.respond(
            status = HttpStatusCode.NotFound,
            message = ErrorResponse.API_KEY_NOT_FOUND_RESPONSE
        )

        call.respond(message = apiKey.info(env = env))
    }
}
