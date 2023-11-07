package com.example.httpinspector.utils

import com.google.gson.Gson
import com.google.gson.JsonParser

object ParserUtils {
    private fun <T> fromJsonString(gson: Gson, tListJson: String?, tClass: Class<T>?): List<T> {
        if (tListJson.isNullOrEmpty()) return ArrayList()
        val jsonListArray = JsonParser.parseString(tListJson).asJsonArray
        if (jsonListArray != null) {
            val list: MutableList<T> = ArrayList()
            for (i in 0 until jsonListArray.size()) list.add(
                gson.fromJson(
                    jsonListArray[i], tClass
                )
            )
            return list
        }
        return ArrayList()
    }

    fun <T> fromJsonString(tListJson: String?, tClass: Class<T>?): List<T> {
        return fromJsonString(Gson(), tListJson, tClass)
    }
}