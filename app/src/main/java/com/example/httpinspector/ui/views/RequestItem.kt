package com.example.httpinspector.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.httpinspector.model.HttpRequest
import com.example.httpinspector.ui.theme.FailureColor
import com.example.httpinspector.ui.theme.HttpMethodStyle
import com.example.httpinspector.ui.theme.RequestTitles
import com.example.httpinspector.ui.theme.SuccessColor

@Composable
fun RequestItem(
    httpRequest: HttpRequest,
    modifier: Modifier,
    isRequestDetails: Boolean = false,
    responseBodyMaxLines: Int = Integer.MAX_VALUE
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        Row {
            Text(
                text = httpRequest.method + "/",
                style = HttpMethodStyle,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = httpRequest.url,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Column {
            Text("Headers :", style = RequestTitles)
            Column {
                httpRequest.requestHeaders.forEachIndexed { index, item ->
                    Text(text = item)
                }
            }
        }
        Row {
            val color =
                if (httpRequest.isSuccessfulRequest()) SuccessColor
                else FailureColor
            Text(
                text = "Code: ", style = RequestTitles,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = httpRequest.code.toString(),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = TextStyle(color = color, fontWeight = FontWeight.Bold)
            )
        }
        val overflow = if (!isRequestDetails) TextOverflow.Ellipsis else TextOverflow.Clip
        val maxLines = if (!isRequestDetails) responseBodyMaxLines else Integer.MAX_VALUE
        if (httpRequest.isSuccessfulRequest()) {
            SuccessResponseBody(
                responseBody = httpRequest.responseBody,
                maxLines = maxLines,
                overflow
            )
        } else {
            ErrorBody(errorMessage = httpRequest.errorMessage, maxLines = maxLines, overflow)
        }
    }
}

@Composable
fun SuccessResponseBody(
    responseBody: String?,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow
) {
    Column {
        Text(
            text = "Response Body :", style = RequestTitles
        )
        Text(
            text = responseBody ?: "",
            maxLines = maxLines,
            overflow = overflow
        )
    }
}

@Composable
fun ErrorBody(
    errorMessage: String?,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow
) {
    Column {
        Text(
            text = "Error message :", style = RequestTitles
        )
        Text(
            text = errorMessage ?: "",
            maxLines = maxLines,
            overflow = overflow
        )
    }
}
