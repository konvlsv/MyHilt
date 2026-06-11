package com.example.myhilt.domain

interface UserRepository {
    suspend fun getUserName(id: Int): String
}