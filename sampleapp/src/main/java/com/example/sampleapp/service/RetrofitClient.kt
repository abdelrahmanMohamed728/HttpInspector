package com.example.sampleapp.service

import com.example.httpinspector.interceptor.HttpInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    fun getRetrofitClient(): PlaceholderService {
        val httpOkHttpClient = OkHttpClient.Builder().addInterceptor(HttpInterceptor()).build()
        val gson = buildGsonObject()
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpOkHttpClient)
            .build()
            .create(PlaceholderService::class.java)
    }

    private fun buildGsonObject(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create()
    }
}