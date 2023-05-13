package com.kfd.jobana.network

import com.kfd.jobana.helpers.Constants
import com.kfd.jobana.models.responses.AuthResponse
import com.kfd.jobana.models.requests.LoginRequest
import com.kfd.jobana.models.requests.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST(Constants.LOGIN_END_POINT)
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ) : AuthResponse

    @POST(Constants.REGISTER_END_POINT)
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ) : AuthResponse
}