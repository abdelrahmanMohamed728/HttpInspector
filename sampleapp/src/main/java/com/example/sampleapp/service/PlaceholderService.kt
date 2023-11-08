package com.example.sampleapp.service

import com.example.sampleapp.model.PostItem
import com.example.sampleapp.model.PostResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface PlaceholderService {
    @GET("posts")
    suspend fun listPosts(): Response<List<PostItem>>
}