package com.example.httpinspector.model

import okhttp3.Request

class RequestMapper {
    companion object {
        fun fromRequestToHttpCall(request: Request): HttpRequest {
            val requestBody = request.body.toString()
            val requestHeader = request.headers.map {
                it.toString()
            }
            return HttpRequest(
                isHttps = request.isHttps,
                requestBody = requestBody,
                requestHeader = requestHeader
            )
        }
    }
}