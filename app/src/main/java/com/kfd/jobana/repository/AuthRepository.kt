package com.kfd.jobana.repository

import com.kfd.jobana.network.AuthApi
import com.kfd.jobana.network.LoginRequest

class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun loginUser(
        email: String,
        password: String
    ) = safeApiCall { api.loginUser(email, password) }

    suspend fun login (
        loginRequest: LoginRequest
    ) = safeApiCall { api.login(loginRequest) }

}