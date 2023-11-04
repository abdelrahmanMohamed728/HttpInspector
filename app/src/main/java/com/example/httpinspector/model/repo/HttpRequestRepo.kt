package com.example.httpinspector.model.repo

import com.example.httpinspector.model.HttpRequest

interface HttpRequestRepo {
    suspend fun addHttpCall(httpRequest: HttpRequest)
    suspend fun addHttpCallResponse(httpRequest: HttpRequest)
    suspend fun deleteHttpCallResponse(httpRequest: HttpRequest)
    suspend fun getRequests():List<HttpRequest>
}