package com.example.httpinspector.model.repo.local

import com.example.httpinspector.model.HttpRequest

interface HttpRequestRepo {
    suspend fun addHttpCall(httpRequest: HttpRequest)
    suspend fun addHttpCallResponse(httpRequest: HttpRequest)
    suspend fun deleteHttpCallResponse(httpRequest: HttpRequest)
    suspend fun getNotSyncedRequests():List<HttpRequest>
    suspend fun getRequests():List<HttpRequest>
    suspend fun markAsSynced()
}