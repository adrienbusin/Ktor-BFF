package com.decathlon.apiKey.dao

import com.decathlon.apiKey.models.ApiKeyDaoModel
import org.litote.kmongo.KMongo
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.getCollection

class ApiKeyDao {
    private val client = KMongo.createClient("mongodb://mongodb:27017")
    private val database = client.getDatabase("ApiKeys")
    private val apiKeyCollection = database.getCollection<ApiKeyDaoModel>()

    fun insert(apiKeyDao: ApiKeyDaoModel) {
        apiKeyCollection.insertOne(apiKeyDao)
    }

    fun insert(apiKeys: List<ApiKeyDaoModel>) {
        apiKeyCollection.insertMany(apiKeys)
    }

    fun getApiKeys(): List<ApiKeyDaoModel> =
        apiKeyCollection.find().toList()

    fun getApiKeyByContext(context: String): ApiKeyDaoModel? =
        apiKeyCollection.find().toList().firstOrNull { it.context == context }

    fun deleteApiKey(context: String): Boolean {
        val deleteResult = apiKeyCollection.deleteOneById(context)
        return deleteResult.deletedCount == 1L
    }
}