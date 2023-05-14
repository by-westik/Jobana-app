package com.kfd.jobana.network

import com.kfd.jobana.models.BaseResponse
import com.kfd.jobana.models.CloseRequest
import com.kfd.jobana.models.requests.AdvertRequest
import com.kfd.jobana.models.responses.AdvertResponse
import com.kfd.jobana.models.responses.AllAdvertsResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AdvertApiService {

    @GET("adverts/my")
    suspend fun getUserAdverts() : List<AdvertResponse>

    @GET("adverts")
    suspend fun getAllAdverts() : AllAdvertsResponse

    @POST("adverts")
    suspend fun createAdvert(
        @Body advertRequest: AdvertRequest
    ) : AdvertResponse

    @PUT("adverts/{id}")
    suspend fun changeAdvert(
        @Path("id") id: String,
        @Body advertRequest: AdvertRequest
    ) : AdvertResponse

    @PATCH("adverts/{id}")
    suspend fun closeOpenAdvert(
        @Path("id") id: String,
        @Body closeRequest: CloseRequest
    ) : BaseResponse

    @DELETE("adverts/{id}")
    suspend fun deleteAdvert(
        @Path("id") id: String
    ) : BaseResponse

    //TODO добавить обработку фоток потом
}