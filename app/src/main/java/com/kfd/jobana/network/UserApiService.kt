package com.kfd.jobana.network

import com.kfd.jobana.models.requests.UserRequest
import com.kfd.jobana.models.responses.UserResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserApiService {

    @GET("user")
    suspend fun getUser() : UserResponse

    @PATCH("user")
    suspend fun changeUserProfile(
        @Body userRequest: UserRequest
    ) : UserResponse

    @GET("user/img")
    suspend fun getUserImage() : ResponseBody

}