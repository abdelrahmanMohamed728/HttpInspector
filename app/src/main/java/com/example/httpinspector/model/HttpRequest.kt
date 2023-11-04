package com.example.httpinspector.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests")
data class HttpRequest(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val requestBody: String,
    val requestHeader: List<String>,
    var responseBody: String? = null,
    val isHttps: Boolean
)