package com.kfd.jobana.models

data class LoginResponse (
    val token: String,
    val status: String,
    val message: String,
    val timestamp: List<Int>
)