package com.kfd.jobana.repository

import com.kfd.jobana.network.AuthApiService
import com.kfd.jobana.models.LoginRequest
import com.kfd.jobana.models.RegisterRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: AuthApiService) :
    BaseRepository() {

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

}