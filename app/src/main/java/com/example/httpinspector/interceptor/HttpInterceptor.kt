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
        val response = try {
            chain.proceed(request)
        } catch (ex: IOException) {
            httpRequest.errorMessage = ex.toString()
            throw ex
        }
        httpRequest.responseBody = response.body?.string()

        httpRequest.code = response.code
        mainScope.launch {
            withContext(Dispatchers.IO) {
                httpRequestRepo.addHttpCall(httpRequest)
            }
        }
        return response
    }
}