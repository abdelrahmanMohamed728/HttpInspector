package com.example.httpinspector.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.httpinspector.model.HttpRequest
import androidx.compose.ui.unit.dp


@Composable
fun requestsView(requests: List<HttpRequest>) {
    Scaffold { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
            items(requests.size) { index ->
                requestItem(requests[index])
            }
        }
    }
}

@Composable
fun requestItem(httpRequest: HttpRequest) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = httpRequest.url)
        Text(text = httpRequest.requestBody)
        Text(text = httpRequest.responseBody ?: "")
    }
}
