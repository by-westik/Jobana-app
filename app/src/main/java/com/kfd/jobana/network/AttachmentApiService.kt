package com.kfd.jobana.network

import com.kfd.jobana.models.BaseResponse
import okhttp3.ResponseBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface AttachmentApiService {

    @GET("files/{attachment_id}")
    suspend fun getAttachment(
        @Path("attachment_id") id: String
    ) : ResponseBody

    @DELETE("files/{attachment_id}")
    suspend fun deleteAttachment(
        @Path("attachment_id") id: String
    ) : BaseResponse
}