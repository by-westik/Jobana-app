package com.kfd.jobana.repository

import com.kfd.jobana.data.UserPreferences
import com.kfd.jobana.models.requests.LoginRequest
import com.kfd.jobana.models.requests.RegisterRequest
import com.kfd.jobana.network.AuthApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: AuthApiService,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun loginUser (
        loginRequest: LoginRequest
    )  = safeApiCall {
        apiService.loginUser(loginRequest)
    }

    suspend fun registerUser (
        registerRequest: RegisterRequest
    ) = safeApiCall {
        apiService.registerUser(registerRequest)
    }

    suspend fun saveUserAuthToken(token: String) {
        preferences.saveUserAuthToken(token)
    }

}