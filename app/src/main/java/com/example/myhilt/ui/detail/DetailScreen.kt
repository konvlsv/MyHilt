package com.example.myhilt.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(
    onBackClick: () -> Unit,
    // Hilt сам создаст DetailViewModel и передаст туда нужный SavedStateHandle
    viewModel: DetailViewModel = hiltViewModel()
) {
    val user by viewModel.userDetailState.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val currentUser = user
        if (currentUser == null) {
            CircularProgressIndicator()
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "Детальная информация")
                Text(text = "ID: ${currentUser.id}")
                Text(text = "Имя: ${currentUser.name}")
                Text(text = "Email: ${currentUser.email}")

                Button(onClick = onBackClick) {
                    Text(text = "Назад")
                }
            }
        }
    }
}