package com.example.myhilt.data

import com.example.myhilt.data.local.UserDao
import com.example.myhilt.data.local.UserEntity
import com.example.myhilt.data.remote.UserApiService
import com.example.myhilt.domain.User
import com.example.myhilt.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService,
    private val userDao: UserDao
) : UserRepository {

    // Стрим данных: UI подписывается на базу данных. Изменилась БД -> обновился UI.
    override fun getUserStream(id: Int): Flow<User?> {
        return userDao.getUserById(id).map { entity ->
            // Если entity не null, превращаем его в доменный User
            entity?.toDomain()
        }
    }

    // Сетевой запрос: скачиваем данные и сохраняем в БД
    override suspend fun refreshUser(id: Int) {
        try {
            val apiUser = apiService.getUserById(id)
            val entity = UserEntity(
                id = apiUser.id,
                name = apiUser.name,
                username = apiUser.username,
                email = apiUser.email
            )
            userDao.insertUser(entity) // Сохраняем в кэш
        } catch (e: Exception) {
            e.printStackTrace()
            // Здесь можно обработать ошибку сети (например, записать лог),
            // но база данных все равно останется доступной для чтения!
        }
    }
}

private fun UserEntity.toDomain(): User {
    return User(
        id = id,
        name = name,
        username = username,
        email = email
    )
}