package com.example.httpinspector.interceptor

import com.example.httpinspector.model.RequestMapper
import com.example.httpinspector.model.repo.HttpRequestRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HttpInterceptor : Interceptor {

    @Inject
    private lateinit var httpRequestRepo: HttpRequestRepo
    private val mainScope = MainScope()

    override fun intercept(chain: Interceptor.Chain): Response {
        val httpRequest = RequestMapper.fromRequestToHttpCall(chain.request())
        mainScope.launch {
            withContext(Dispatchers.IO) {
                httpRequestRepo.addHttpCall(httpRequest)
            }
        }
        val response = try {
            chain.proceed(chain.request())
        } catch (ex: IOException) {
            //TODO cache the exception
            throw ex
        }
        httpRequest.responseBody = response.body.toString()
        mainScope.launch {
            withContext(Dispatchers.IO) {
                httpRequestRepo.addHttpCallResponse(httpRequest)
            }
        }
        return response
    }
}