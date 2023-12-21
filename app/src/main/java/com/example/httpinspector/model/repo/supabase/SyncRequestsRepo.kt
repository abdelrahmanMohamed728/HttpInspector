package com.example.httpinspector.model.repo.supabase

import com.example.httpinspector.model.HttpRequestDTO

interface SyncRequestsRepo {
    suspend fun sendRequests(requests: List<HttpRequestDTO>)
}