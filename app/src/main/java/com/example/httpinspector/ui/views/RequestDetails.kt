package com.example.httpinspector.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.httpinspector.model.HttpRequest
import com.example.httpinspector.ui.theme.HttpMethodStyle

@Composable
fun RequestDetailsView(modifier: Modifier, httpRequest: HttpRequest) {
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
            SuccessResponseBody(responseBody = httpRequest.responseBody)
        } else {
            ErrorBody(errorMessage = httpRequest.errorMessage)
        }
    }
}