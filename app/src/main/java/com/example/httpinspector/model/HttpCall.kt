package com.example.httpinspector.model

data class HttpCall(
    val requestBody: String,
    val requestHeader: List<String>,
    var responseBody: String? = null,
    val isHttps: Boolean
)