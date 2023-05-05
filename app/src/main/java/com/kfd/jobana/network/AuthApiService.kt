package com.kfd.jobana.network

import com.kfd.jobana.helpers.Constants
import com.kfd.jobana.models.LoginRequest
import com.kfd.jobana.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST(Constants.LOGIN_END_POINT)
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>

}