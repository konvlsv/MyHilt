package com.example.myhilt.ui.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {
    val user by viewModel.userState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Умная проверка на null
        val currentUser = user
        if (currentUser == null) {
            CircularProgressIndicator() // Крутилка пока база пустая и идет запрос
        } else {
            UserScreenContent(user = currentUser)
        }
    }
}

@Composable
fun UserScreenContent(user: User) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Имя: ${user.name}")
        Text(text = "Никнейм: ${user.username}")
        Text(text = "Email: ${user.email}")
    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    UserScreenContent(
        user = User(
            1,
            "John Doe",
            "johndoe",
            "james.monroe@examplepetstore.com"
        )
    )
}