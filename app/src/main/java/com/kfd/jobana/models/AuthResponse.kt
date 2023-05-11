package com.kfd.jobana.models

// TODO доавить enum клас для Response чтобы обрабатывать случаи, когла сервер не работает и другие ошибки
data class AuthResponse (
    val token: String,
    val status: String,
    val message: String,
    val timestamp: List<Int>
)