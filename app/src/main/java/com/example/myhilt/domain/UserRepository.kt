package com.example.myhilt.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserStream(id: Int): Flow<User?>
    suspend fun refreshUser(id: Int) // Метод для принудительного обновления из сети
}