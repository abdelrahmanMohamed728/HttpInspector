package com.example.httpinspector.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.httpinspector.model.HttpRequest
import com.example.httpinspector.model.repo.HttpRequestRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel constructor(
    private val httpRequestRepo: HttpRequestRepo
) : ViewModel() {

    val requestsMutableState = mutableStateOf<List<HttpRequest>>(listOf())

    fun getRequests() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    requestsMutableState.value = httpRequestRepo.getRequests()
                } catch (ex: Exception) {
                    //TODO handle exceptions
                }
            }
        }
    }

}