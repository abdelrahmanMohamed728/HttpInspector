package com.example.httpinspector.model.repo.supabase

import com.example.httpinspector.model.HttpRequestDTO
import com.example.httpinspector.utils.SupabaseClient

class SyncRequestsRepoImpl : SyncRequestsRepo {
    private val supabaseClient = SupabaseClient.getInstance()

    override suspend fun sendRequests(requests: List<HttpRequestDTO>) {
        supabaseClient.insertRequests(SupabaseTables.REQUESTS.tableName, requests)
    }
}