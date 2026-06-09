package com.example.myhilt.data

import com.example.myhilt.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(): UserRepository {
    override fun getUserName(): String {
        return "Konstantin"
    }
}