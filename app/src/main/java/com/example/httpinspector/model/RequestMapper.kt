package com.example.httpinspector.model

import okhttp3.Request

class RequestMapper {
    companion object {
        fun fromRequestToHttpCall(request: Request): HttpCall {
            val requestBody = request.body.toString()
            val requestHeader = request.headers.map {
                it.toString()
            }
            return HttpCall(
                isHttps = request.isHttps,
                requestBody = requestBody,
                requestHeader = requestHeader
            )
        }
    }
}