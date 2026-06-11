package com.example.myhilt.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    // Хилт сам прокинет сюда репозиторий, так как мы настроили его раньше
    private val repository: UserRepository
) {
    // Возвращает поток с объектом User?
    fun getUserStream(userId: Int): Flow<User?> {
        return repository.getUserStream(userId).map { user ->
            // Здесь можно провести дополнительную модификацию объекта, если нужно
            user?.copy(name = user.name.uppercase())
        }
    }

    // Метод для обновления
    suspend fun refresh(userId: Int) {
        repository.refreshUser(userId)
    }
}