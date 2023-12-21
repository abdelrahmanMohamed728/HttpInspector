package com.example.sampleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.httpinspector.HttpInspectorBuilder
import com.example.httpinspector.ui.views.HttpInspectorFloatingButton
import com.example.sampleapp.service.PlaceholderService
import com.example.sampleapp.service.RetrofitClient
import com.example.sampleapp.ui.theme.Http_inspectorTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HttpInspectorBuilder.getInstance()
            .initialize(applicationContext, "62c9b701-58fb-4f44-bdb8-dd4f3f368376")
        val client = RetrofitClient.getInstance().create(PlaceholderService::class.java)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val posts = client.listPosts()
                    Log.d("posts", posts.toString())
                } catch (ex: Exception) {
                    Log.e("posts", ex.message.toString())
                }
            }
        }
        setContent {
            Http_inspectorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HttpInspectorFloatingButton(modifier = Modifier)
                }
            }
        }
    }
}
