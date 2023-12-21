package com.example.httpinspector.model

import kotlinx.serialization.Serializable

@Serializable
data class HttpRequestDTO(
    val user_id: String,
    val method: String,
    var code: Int = 0,
    val url: String,
    val request_body: String,
    val request_headers: String,
    var response_body: String? = null,
    val is_https: Boolean,
    var error_message: String? = null
)