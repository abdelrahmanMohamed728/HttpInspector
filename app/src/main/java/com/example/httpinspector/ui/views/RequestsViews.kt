package com.example.httpinspector.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.httpinspector.model.HttpRequest
import androidx.compose.ui.unit.dp
import com.example.httpinspector.ui.theme.FailureColor
import com.example.httpinspector.ui.theme.HttpMethodStyle
import com.example.httpinspector.ui.theme.RequestTitles
import com.example.httpinspector.ui.theme.SuccessColor


@Composable
fun RequestsView(requests: List<HttpRequest>) {
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            val selectedRequest = remember { mutableStateOf<HttpRequest?>(null) }
            if (selectedRequest.value == null) {
                Column(
                    Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    requests.forEachIndexed { index, httpRequest ->
                        RequestItem(
                            httpRequest,
                            Modifier.clickable {
                                selectedRequest.value = httpRequest
                            },
                            isRequestDetails = false,
                            responseBodyMaxLines = 5
                        )
                        if (index < requests.lastIndex)
                            HorizontalDivider(
                                color = Color.Black,
                                thickness = 1.dp,
                                modifier = Modifier
                                    .padding(12.dp)

                            )
                    }
                }
            } else {
                RequestItem(
                    httpRequest = selectedRequest.value!!,
                    modifier = Modifier
                        .clickable {
                            selectedRequest.value = null
                        }
                        .verticalScroll(rememberScrollState()),
                    isRequestDetails = true
                )
            }
        }
    }
}

