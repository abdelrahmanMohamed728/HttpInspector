package com.example.httpinspector.utils

import com.example.httpinspector.BuildConfig
import com.example.httpinspector.model.HttpRequestDTO
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json

class SupabaseClient {
    private val supabaseClient =
        createSupabaseClient(
            BuildConfig.SUPABASE_URL,
            BuildConfig.SUPABASE_KEY
        ) {
            install(Postgrest)
            defaultSerializer = KotlinXSerializer(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

    suspend fun insertRequests(tableName: String, obj: Any) {
        supabaseClient.from(tableName).insert(obj)
    }

    suspend fun insertRequests(tableName: String, dataList: List<HttpRequestDTO>) {
        supabaseClient.from(tableName).insert(dataList)
    }
}