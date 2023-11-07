package com.example.httpinspector.local

import androidx.room.TypeConverter
import com.example.httpinspector.utils.ParserUtils
import com.google.gson.Gson

class HttpConverter {
    @TypeConverter
    fun fromStringList(source: List<String>?): String? {
        return if (source == null) null else Gson().toJson(source)
    }

    @TypeConverter
    fun toStringList(string: String?): List<String>? {
        return  if (string == null) null else ParserUtils.fromJsonString(string, String::class.java)
    }
}