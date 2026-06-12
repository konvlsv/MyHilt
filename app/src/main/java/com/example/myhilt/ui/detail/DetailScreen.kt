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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myhilt.domain.User

@Composable
fun DetailScreen(
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val user by viewModel.userDetailState.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val currentUser = user
        if (currentUser == null) {
            CircularProgressIndicator()
        } else {
            // Вынесли контент в отдельную Composable функцию
            DetailScreenContent(user = currentUser, onBackClick = onBackClick)
        }
    }
}

@Composable
fun DetailScreenContent(user: User, onBackClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Детальная информация")
        Text(text = "ID: ${user.id}")
        Text(text = "Имя: ${user.name}")
        Text(text = "Email: ${user.email}")

        Button(onClick = onBackClick) {
            Text(text = "Назад")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreenContent(
        user = User(1, "John Doe", "johndoe", "james.monroe@example.com"),
        onBackClick = {}
    )
}