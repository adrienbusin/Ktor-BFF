package com.decathlon.apiKey

import kotlinx.serialization.Serializable

class ApiKeyUtils {
    companion object {
        const val ENV = "env"
        const val CONTEXT = "context"
        const val PROD = "prod"
    }
}

@Serializable
data class ErrorResponse(val message: String) {
    companion object {
        val API_KEY_NOT_FOUND_RESPONSE = ErrorResponse("No api key was not found")
        val BAD_REQUEST_MISSING_ENV_RESPONSE = ErrorResponse("Missing Env")
        val BAD_REQUEST_MISSING_CONTEXT_RESPONSE = ErrorResponse("Missing context")
    }
}

@Serializable
data class SuccessResponse(val message: String) {
    companion object {
        val API_KEY_SAVED_RESPONSE = ErrorResponse("ApiKey saved correctly")
        val API_KEY_DELTED_RESPONSE = ErrorResponse("ApiKey removed correctly")
    }
}