package com.kfd.jobana.repository

import com.kfd.jobana.network.AuthApiService
import com.kfd.jobana.models.LoginRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: AuthApiService) {

    suspend fun loginUser (
        loginRequest: LoginRequest
    )  = apiService.loginUser(loginRequest)

}