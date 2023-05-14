package com.kfd.jobana.data

import com.kfd.jobana.models.responses.AdvertResponse
import okhttp3.ResponseBody
import java.io.InputStream

data class AdvertItem(
    val id : String,
    val authorId: String,
    val title: String,
    val shortDescription: String,
    val description: String,
    val createdAt : String,
    val attachments : List<ByteArray>,
    val price: Int,
    val city: String?,
    val categories : List<String>,
    val isClosed : Boolean
)