package com.example.httpinspector.model.repo.supabase

import com.example.httpinspector.model.HttpRequestDTO
import com.example.httpinspector.utils.SupabaseClient

class SyncRequestsRepoImpl : SyncRequestsRepo {
    override suspend fun sendRequests(requests: List<HttpRequestDTO>) {
        SupabaseClient().insertRequests(SupabaseTables.REQUESTS.tableName, requests)
    }
}