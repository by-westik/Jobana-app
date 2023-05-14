package com.kfd.jobana.repository

import com.kfd.jobana.models.requests.UserRequest
import com.kfd.jobana.network.UserApiService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApiService: UserApiService
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        userApiService.getUser()
    }

    suspend fun getUserImage() = safeApiCall {
        userApiService.getUserImage()
    }

    suspend fun changeUserProfile(userRequest: UserRequest) = safeApiCall {
        userApiService.changeUserProfile(userRequest)
    }
}