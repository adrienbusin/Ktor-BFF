package com.decathlon.apiKey.routes

import com.decathlon.apiKey.dao.ApiKeyDao
import com.decathlon.apiKey.ApiKeyUtils.Companion.CONTEXT
import com.decathlon.apiKey.ErrorResponse
import com.decathlon.apiKey.SuccessResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteApiKeyRouting() {
    val service = ApiKeyDao()
    delete("apiKeys/{context?}") {
        val context = call.parameters[CONTEXT] ?: return@delete call.respond(
            status = HttpStatusCode.BadRequest, message = ErrorResponse.BAD_REQUEST_MISSING_ENV_RESPONSE
        )

        if (service.deleteApiKey(context)) {
            call.respond(status = HttpStatusCode.Accepted, message = SuccessResponse.API_KEY_DELTED_RESPONSE)
        } else {
            call.respond(status = HttpStatusCode.NotFound, message = ErrorResponse.API_KEY_NOT_FOUND_RESPONSE)
        }
    }

}