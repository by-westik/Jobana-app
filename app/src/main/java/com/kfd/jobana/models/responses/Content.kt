package com.kfd.jobana.models.responses

data class Content(
    val attachments: List<String>,
    val authorId: String,
    val categories: List<String>,
    val city: String,
    val createdAt: String,
    val description: String,
    val id: String,
    val isClosed: Boolean,
    val price: Int,
    val shortDescription: String,
    val title: String
)