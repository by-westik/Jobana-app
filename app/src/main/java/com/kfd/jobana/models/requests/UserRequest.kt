package com.kfd.jobana.models.requests

data class UserRequest(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val gender: String
)
