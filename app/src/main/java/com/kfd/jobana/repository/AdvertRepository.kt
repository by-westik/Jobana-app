package com.kfd.jobana.repository

import com.kfd.jobana.data.UserPreferences
import com.kfd.jobana.models.CloseRequest
import com.kfd.jobana.models.requests.AdvertRequest
import com.kfd.jobana.network.AdvertApiService
import javax.inject.Inject

class AdvertRepository @Inject constructor(
    private val apiService: AdvertApiService
) : BaseRepository() {

    suspend fun getUserAdverts() = safeApiCall {
        apiService.getUserAdverts()
    }

    suspend fun createAdvert(advertRequest: AdvertRequest) = safeApiCall {
        apiService.createAdvert(advertRequest)
    }

    suspend fun changeAdvert(id: String, advertRequest: AdvertRequest) = safeApiCall {
        apiService.changeAdvert(id, advertRequest)
    }

    suspend fun closeOpenRequest(id: String, isClosed: Boolean) = safeApiCall {
        apiService.closeOpenAdvert(id, CloseRequest(isClosed))
    }

    suspend fun deleteAdvert(id: String) = safeApiCall {
        apiService.deleteAdvert(id)
    }
}