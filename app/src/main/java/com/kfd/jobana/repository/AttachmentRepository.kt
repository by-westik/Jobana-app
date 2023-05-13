package com.kfd.jobana.repository

import com.kfd.jobana.network.AttachmentApiService
import javax.inject.Inject

class AttachmentRepository @Inject constructor(
    private val attachmentApiService: AttachmentApiService
) : BaseRepository() {

    suspend fun getAttachment(id: String) = safeApiCall {
        attachmentApiService.getAttachment(id)
    }

    suspend fun deleteAttachment(id: String) = safeApiCall {
        attachmentApiService.deleteAttachment(id)
    }
}