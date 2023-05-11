package com.kfd.jobana.models

import java.time.LocalDate

data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val gender: String
)
