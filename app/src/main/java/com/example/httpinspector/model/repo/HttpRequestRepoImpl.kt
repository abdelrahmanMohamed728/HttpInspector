package com.example.httpinspector.model.repo

import com.example.httpinspector.local.HttpRequestsDao
import com.example.httpinspector.model.HttpRequest

class HttpRequestRepoImpl constructor(
    val requestsDao: HttpRequestsDao
) : HttpRequestRepo {

    override suspend fun addHttpCall(httpRequest: HttpRequest) {
        requestsDao.insertRequest(httpRequest)
    }

    override suspend fun addHttpCallResponse(httpRequest: HttpRequest) {
        requestsDao.updateRequest(httpRequest)
    }

    override suspend fun deleteHttpCallResponse(httpRequest: HttpRequest) {
        requestsDao.deleteRequest(httpRequest)
    }

    override suspend fun getRequests(): List<HttpRequest> {
        return requestsDao.getRequests()
    }
}