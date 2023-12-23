package com.example.httpinspector.model.repo.supabase

import com.example.httpinspector.model.HttpRequestDTO
import com.example.httpinspector.utils.SupabaseClient

class SyncRequestsRepoImpl : SyncRequestsRepo {
    private val supabaseClient = SupabaseClient()

    override suspend fun sendRequests(requests: List<HttpRequestDTO>) {
        supabaseClient.authorize("ahmed@gmail.com","Ahmed@1234")
        SupabaseClient().insertRequests(SupabaseTables.REQUESTS.tableName, requests)
    }
}