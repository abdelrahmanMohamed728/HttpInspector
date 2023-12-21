package com.example.httpinspector.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests")
data class HttpRequest(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val method: String,
    var code: Int = 0,
    val url: String,
    val requestBody: String,
    val requestHeaders: List<String>,
    var responseBody: String? = null,
    val isHttps: Boolean,
    var errorMessage: String? = null,
    var isSynced: Boolean = false
) {
    fun isSuccessfulRequest(): Boolean {
        return code == 200 || code == 204
    }
}