package com.example.myhilt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): Flow<UserEntity?> // Возвращает Flow, реагирует на изменения

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Если юзер есть, перезапишет его
    suspend fun insertUser(user: UserEntity)
}