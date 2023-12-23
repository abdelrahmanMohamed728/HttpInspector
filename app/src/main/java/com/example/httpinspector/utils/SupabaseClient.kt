package com.example.httpinspector.utils

import com.example.httpinspector.BuildConfig
import com.example.httpinspector.model.HttpRequestDTO
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
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
            install(Auth)
            install(Postgrest)
            defaultSerializer = KotlinXSerializer(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

    suspend fun authorize(email: String,password: String){
        supabaseClient.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun insertRequests(tableName: String, obj: Any) {
        supabaseClient.from(tableName).insert(obj)
    }

    suspend fun insertRequests(tableName: String, dataList: List<HttpRequestDTO>) {
        supabaseClient.from(tableName).insert(dataList)
    }
}