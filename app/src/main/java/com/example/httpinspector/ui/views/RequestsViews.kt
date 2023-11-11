package com.example.httpinspector.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.httpinspector.ui.theme.HttpMethodStyle
import kotlin.math.max


@Composable
fun RequestsView(requests: List<HttpRequest>) {
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            val selectedRequest = remember { mutableStateOf<HttpRequest?>(null) }
            if (selectedRequest.value == null) {
                LazyColumn(Modifier.padding(12.dp)) {
                    items(requests.size) { index ->
                        val currentRequest = requests[index]
                        RequestItem(currentRequest,
                            Modifier.clickable {
                                selectedRequest.value = currentRequest
                            })
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
                RequestDetailsView(
                    httpRequest = selectedRequest.value!!,
                    modifier = Modifier.clickable {
                        selectedRequest.value = null
                    })
            }
        }
    }
}

@Composable
fun RequestItem(httpRequest: HttpRequest, modifier: Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = modifier) {
        Row {
            Text(
                text = httpRequest.method + "/",
                style = HttpMethodStyle
            )
            Text(
                text = httpRequest.url,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Row {
            val color =
                if (httpRequest.isSuccessfulRequest()) Color.Green else Color.Red
            Text(
                text = "Code: ",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = httpRequest.code.toString(),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = TextStyle(color = color, fontWeight = FontWeight.Bold)
            )
        }
        if (httpRequest.isSuccessfulRequest()) {
            SuccessResponseBody(responseBody = httpRequest.responseBody, maxLines = 5)
        } else {
            ErrorBody(errorMessage = httpRequest.errorMessage, maxLines = 5)
        }
    }
}

@Composable
fun SuccessResponseBody(responseBody: String?, maxLines: Int = Int.MAX_VALUE) {
    Column {
        Text(
            text = "Response Body :",
        )
        Text(
            text = responseBody ?: "",
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ErrorBody(errorMessage: String?, maxLines: Int = Int.MAX_VALUE) {
    Column {
        Text(
            text = "Error message :",
        )
        Text(
            text = errorMessage ?: "",
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}
