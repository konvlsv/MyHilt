package com.example.myhilt.data

import com.example.myhilt.data.remote.UserApiService
import com.example.myhilt.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService
): UserRepository {
    override suspend fun getUserName(id: Int): String {
        return try {
            val response = apiService.getUserById(id)
            response.name // Возвращаем имя, пришедшее из сети
        } catch (e: Exception) {
            e.printStackTrace()
            "Ошибка загрузки"
        }
    }
}