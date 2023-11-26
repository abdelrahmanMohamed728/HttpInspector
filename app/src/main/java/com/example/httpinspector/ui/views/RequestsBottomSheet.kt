package com.example.httpinspector.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.httpinspector.model.HttpRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestsBottomSheet(
    requests: List<HttpRequest>,
    sheetState: SheetState,
    scope: CoroutineScope,
    onHideSheet: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            onHideSheet()
        },
        sheetState = sheetState,
    ) {
        Column {
            RequestsView(requests = requests)
            Button(
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp),
                onClick = {
                    scope.launch { sheetState.expand() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onHideSheet()
                        }
                    }
                },
            ) {
                Text("Close bottom sheet")
            }
        }
    }
}