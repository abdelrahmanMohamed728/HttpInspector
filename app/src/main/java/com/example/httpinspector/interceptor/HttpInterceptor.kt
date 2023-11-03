package com.example.httpinspector.interceptor

import com.example.httpinspector.model.RequestMapper
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val httpCall = RequestMapper.fromRequestToHttpCall(chain.request())

    }
}