package com.decathlon.apiKey.models

import com.decathlon.apiKey.ApiKeyUtils.Companion.PROD
import org.bson.codecs.pojo.annotations.BsonId

@kotlinx.serialization.Serializable
data class ApiKeyDaoModel(
    @BsonId
    val context: String,
    val team: String,
    val assignment: String,
    val service: String,
    val prodKey: String,
    val preprodKey: String
)

@kotlinx.serialization.Serializable
data class ApiKeyFormatted(
    val context: String, val team: String, val assignment: String, val service: String, val key: String
)

fun ApiKeyDaoModel.info(env: String) = ApiKeyFormatted(
    context = context,
    team = team,
    assignment = assignment,
    service = service,
    key = if (env == PROD) prodKey else preprodKey
)
