package com.example.myhilt.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyExampleScreen(
    viewModel: MyExampleViewModel = hiltViewModel()
) {
    val userName by viewModel.userName.collectAsState()
    MyExampleScreenContent(name = userName)
}

@Composable
fun MyExampleScreenContent(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Привет, $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun MyExampleScreenPreview() {
    MyExampleScreenContent("Test")
}