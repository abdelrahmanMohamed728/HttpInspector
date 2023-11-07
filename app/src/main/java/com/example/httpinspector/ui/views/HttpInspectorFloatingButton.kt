package com.example.httpinspector.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.httpinspector.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.httpinspector.local.HttpDatabase
import com.example.httpinspector.model.repo.HttpRequestRepoImpl
import com.example.httpinspector.ui.viewmodel.viewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun httpInspectorFloatingButton(modifier: Modifier) {
    val dao = HttpDatabase.getDatabase(LocalContext.current).requestsDao()
    val repo = HttpRequestRepoImpl(dao)
    val viewModel: MainViewModel = viewModel(factory = viewModelFactory {
        MainViewModel(repo)
    })
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show Http Requests") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    viewModel.getRequests()
                    showBottomSheet = true
                }
            )
        },
        modifier = modifier
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState,

                    ) {
                    Column {
                        requestsView(requests = viewModel.requestsMutableState.value)
                    }
                    Button(modifier = Modifier
                        .height(20.dp)
                        .width(20.dp), onClick = {
                        scope.launch { sheetState.expand() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Close bottom sheet")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun previewHttpFloatingButton() {
    httpInspectorFloatingButton(modifier = Modifier)
}