package com.decathlon.apiKey.routes

import com.decathlon.apiKey.dao.ApiKeyDao
import com.decathlon.apiKey.models.ApiKeyDaoModel
import com.decathlon.apiKey.SuccessResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.postApiKeyRouting() {
    val service = ApiKeyDao()

    post("apiKeys") {
        try {
            val apiKeys = call.receive<List<ApiKeyDaoModel>>()
            service.insert(apiKeys)
        } catch (_: Exception) {
            val apiKey = call.receive<ApiKeyDaoModel>()
            service.insert(apiKey)
        }

        call.respond(status = HttpStatusCode.Created, message = SuccessResponse.API_KEY_SAVED_RESPONSE)
    }
}