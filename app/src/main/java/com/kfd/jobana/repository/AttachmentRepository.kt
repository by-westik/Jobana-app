package com.kfd.jobana.repository

import com.kfd.jobana.network.AttachmentApiService
import okhttp3.ResponseBody
import javax.inject.Inject

class AttachmentRepository @Inject constructor(
    private val attachmentApiService: AttachmentApiService
) : BaseRepository() {

    //fun getAttachment(id: String) = attachmentApiService.getAttachment(id)


    suspend fun deleteAttachment(id: String) = safeApiCall {
        attachmentApiService.deleteAttachment(id)
    }

}