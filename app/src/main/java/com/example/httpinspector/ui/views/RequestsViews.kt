package com.example.httpinspector.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.httpinspector.model.HttpRequest
import androidx.compose.ui.unit.dp
import com.example.httpinspector.ui.theme.HttpMethodStyle


@Composable
fun requestsView(requests: List<HttpRequest>) {
    Scaffold { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
            items(requests.size) { index ->
                requestItem(requests[index])
                if (index < requests.lastIndex)
                    Divider(
                        color = Color.Black,
                        thickness = 1.dp,
                        modifier = Modifier.padding(12.dp)
                    )
            }
        }
    }
}

@Composable
fun requestItem(httpRequest: HttpRequest) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
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
            SuccessResponseBody(responseBody = httpRequest.responseBody)
        }
        else {
            ErrorBody(errorMessage = httpRequest.errorMessage)
        }
    }
}

@Composable
fun SuccessResponseBody(responseBody: String?) {
    Column {
        Text(
            text = "Response Body :",
        )
        Text(
            text = responseBody ?: "",
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ErrorBody(errorMessage: String?) {
    Column {
        Text(
            text = "Error message :",
        )
        Text(
            text = errorMessage ?: "",
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}
