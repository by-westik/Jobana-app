package com.kfd.jobana.models.responses



data class AdvertResponse(
    val id : String,
    val authorId: String,
    val title: String,
    val shortDescription: String,
    val description: String,
    val createdAt : String,
    val attachments : List<String>,
    val price: Int,
    val city: String?,
    val categories : List<String>,
    val isClosed : Boolean
)