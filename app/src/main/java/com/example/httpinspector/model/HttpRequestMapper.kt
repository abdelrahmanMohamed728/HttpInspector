package com.example.httpinspector.model

class HttpRequestMapper {
    fun toDto(httpRequest: HttpRequest, userId: String): HttpRequestDTO {
        return HttpRequestDTO(
            user_id = userId,
            method = httpRequest.method,
            code = httpRequest.code,
            is_https = httpRequest.isHttps,
            request_body = httpRequest.requestBody,
            request_headers = httpRequest.requestHeaders.toString(),
            error_message = httpRequest.errorMessage,
            response_body = httpRequest.responseBody,
            url = httpRequest.url
        )
    }
}