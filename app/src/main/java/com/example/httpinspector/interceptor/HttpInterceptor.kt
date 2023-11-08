package com.example.httpinspector.interceptor

import com.example.httpinspector.local.HttpDatabase
import com.example.httpinspector.model.RequestMapper
import com.example.httpinspector.model.repo.HttpRequestRepo
import com.example.httpinspector.model.repo.HttpRequestRepoImpl
import com.example.httpinspector.utils.ContextManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HttpInterceptor : Interceptor {

    private val dao = HttpDatabase.getDatabase(ContextManager.getInstance().getContext()).requestsDao()
    private var httpRequestRepo = HttpRequestRepoImpl(dao)
    private val mainScope = MainScope()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpRequest = RequestMapper.fromRequestToHttpCall(request)
        mainScope.launch {
            withContext(Dispatchers.IO) {
                httpRequestRepo.addHttpCall(httpRequest)
            }
        }
        val response = try {
            chain.proceed(request)
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