package com.example.myhilt.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    // Хилт сам прокинет сюда репозиторий, так как мы настроили его раньше
    private val repository: UserRepository
) {
    // Возвращаем Flow со строкой
    fun getNameStream(userId: Int): Flow<String> {
        return repository.getUserNameStream(userId).map { name ->
            name?.uppercase() ?: "ЗАГРУЗКА..."
        }
    }

    // Метод для обновления
    suspend fun refresh(userId: Int) {
        repository.refreshUser(userId)
    }
}