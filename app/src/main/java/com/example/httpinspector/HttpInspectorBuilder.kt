package com.example.httpinspector

import android.content.Context
import com.example.httpinspector.local.HttpDatabase
import com.example.httpinspector.model.HttpRequestMapper
import com.example.httpinspector.model.repo.local.HttpRequestRepoImpl
import com.example.httpinspector.model.repo.supabase.SyncRequestsRepoImpl
import com.example.httpinspector.utils.ContextManager
import com.example.httpinspector.utils.SupabaseClient
import com.example.httpinspector.utils.BaseUser
import com.example.httpinspector.utils.network.ConnectivityObserver
import com.example.httpinspector.utils.network.IConnectivityObserver
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HttpInspectorBuilder {

    companion object {
        @Volatile
        private var instance: HttpInspectorBuilder? = null
        fun getInstance(): HttpInspectorBuilder {
            return instance ?: synchronized(this) {
                instance ?: HttpInspectorBuilder().also { instance = it }
            }
        }
    }

    suspend fun initialize(context: Context, email: String, password: String) {
        SupabaseClient.getInstance().authorize(email, password)
        ContextManager.getInstance().initialize(context)
        initNetworkObserver()
    }

    private fun initNetworkObserver() {
        val currentContext = ContextManager.getInstance().getContext()
        val connectivityObserver = ConnectivityObserver()
        connectivityObserver.observe().onEach {
            if (it == IConnectivityObserver.Status.Available) {
                sendRequests(currentContext)
            }
        }.launchIn(MainScope())
    }

    private suspend fun sendRequests(context: Context) {
        val dao = HttpDatabase.getDatabase(context).requestsDao()
        val localRepo = HttpRequestRepoImpl(dao)
        val mapper = HttpRequestMapper()
        val requests =
            localRepo.getNotSyncedRequests().map { mapper.toDto(it, BaseUser.user?.id ?: "") }
        val syncRepo = SyncRequestsRepoImpl()
        syncRepo.sendRequests(requests)
        localRepo.markAsSynced()
    }
}