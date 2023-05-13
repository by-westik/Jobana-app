package com.kfd.jobana.models.requests

data class AdvertRequest(
    val title : String,
    val shortDescription : String,
    val description : String,
    val price : Int,
    val city : String?,
    val categories : List<String>
)