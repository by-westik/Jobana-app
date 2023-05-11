package com.kfd.jobana.network

import com.kfd.jobana.helpers.Constants
import com.kfd.jobana.models.LoginRequest
import com.kfd.jobana.models.LoginResponse
import com.kfd.jobana.models.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST(Constants.LOGIN_END_POINT)
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>

    @POST(Constants.REGISTER_END_POINT)
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ) : Response<LoginResponse>

}