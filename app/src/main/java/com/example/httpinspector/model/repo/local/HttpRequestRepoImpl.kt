package com.example.httpinspector.model.repo.local

import com.example.httpinspector.local.HttpRequestsDao
import com.example.httpinspector.model.HttpRequest

class HttpRequestRepoImpl(
    private val requestsDao: HttpRequestsDao
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

    override suspend fun getNotSyncedRequests(): List<HttpRequest> {
        return requestsDao.getNotSyncedRequests()
    }

    override suspend fun getRequests(): List<HttpRequest> {
        return requestsDao.getRequests()
    }

    override suspend fun markAsSynced() {
        requestsDao.markAsSynced()
    }
}