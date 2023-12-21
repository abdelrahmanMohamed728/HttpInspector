package com.example.httpinspector.utils.network

import com.example.httpinspector.R
import kotlinx.coroutines.flow.Flow

interface IConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available,
        Unavailable,
        Lost
    }
}