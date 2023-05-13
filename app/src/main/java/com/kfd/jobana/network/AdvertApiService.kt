package com.kfd.jobana.network

import com.kfd.jobana.models.BaseResponse
import com.kfd.jobana.models.CloseRequest
import com.kfd.jobana.models.requests.AdvertRequest
import com.kfd.jobana.models.responses.AdvertResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT

interface AdvertApiService {

    @GET("adverts/my")
    suspend fun getUserAdverts() : List<AdvertResponse>

    @POST("adverts")
    suspend fun createAdvert(
        @Body advertRequest: AdvertRequest
    ) : AdvertResponse

    @PUT("adverts/{id}")
    suspend fun changeAdvert(
        id: String,
        @Body advertRequest: AdvertRequest
    ) : AdvertResponse

    @PATCH("adverts/{id}")
    suspend fun closeOpenAdvert(
        id: String,
        @Body closeRequest: CloseRequest
    ) : BaseResponse

    @DELETE("adverts/{id}")
    suspend fun deleteAdvert(
        id: String
    ) : BaseResponse

    //TODO добавить обработку фоток потом
}