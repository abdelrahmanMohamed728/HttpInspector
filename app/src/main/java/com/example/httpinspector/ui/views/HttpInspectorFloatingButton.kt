package com.example.httpinspector.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.httpinspector.ui.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.httpinspector.R
import com.example.httpinspector.local.HttpDatabase
import com.example.httpinspector.model.repo.local.HttpRequestRepoImpl
import com.example.httpinspector.ui.viewmodel.viewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HttpInspectorFloatingButton(modifier: Modifier) {
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
            FloatingActionButton(
                shape = CircleShape,
                modifier = Modifier.width(48.dp).height(48.dp),
                onClick = {
                    viewModel.getRequests()
                    showBottomSheet = true
                }
            ) {
                Image(painterResource(R.drawable.web_icon),"Show Requests")
            }
        },
        modifier = modifier
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            if (showBottomSheet) {
                RequestsBottomSheet(
                    requests = viewModel.requestsMutableState.value,
                    sheetState = sheetState,
                    scope = scope
                ) {
                    showBottomSheet = false
                }
            }
        }
    }
}

@Preview
@Composable
fun previewHttpFloatingButton() {
    HttpInspectorFloatingButton(modifier = Modifier)
}