package com.example.myhilt.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserNameStream(id: Int): Flow<String?>
    suspend fun refreshUser(id: Int) // Метод для принудительного обновления из сети
}