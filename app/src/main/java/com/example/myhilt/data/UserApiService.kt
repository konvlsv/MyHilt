package com.example.myhilt.data

import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserResponse
}