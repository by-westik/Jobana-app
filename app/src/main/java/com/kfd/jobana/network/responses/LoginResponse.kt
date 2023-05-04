package com.kfd.jobana.network.responses

data class LoginResponse (
    val token: String,
    val status: String,
    val message: String,
    val timestamp: List<Int>
)