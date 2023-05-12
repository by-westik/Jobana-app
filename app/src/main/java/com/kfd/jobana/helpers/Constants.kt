package com.kfd.jobana.helpers

object Constants {
    // TODO изменить адрес, когда выложим проект
    const val BASE_URL = "http://192.168.0.102:8000/api/"
    const val LOGIN_END_POINT = "auth/login"
    const val REGISTER_END_POINT = "auth/signup"

    const val GENDER_MALE = "M"
    const val GENDER_FEMALE = "F"

    const val DATE_FORMAT = "dd-MM-yyyy"
    const val EMAIL_REGEX = "^[\\w-.]+@([\\w-]+.)+[\\w-]+$"
}