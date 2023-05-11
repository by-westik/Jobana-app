package com.kfd.jobana.models

import okhttp3.ResponseBody
import retrofit2.Response

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?, //TODO добавить сюда enum класс ошибок?
        val error: ResponseBody?
    ) : Resource<Nothing>()

}
