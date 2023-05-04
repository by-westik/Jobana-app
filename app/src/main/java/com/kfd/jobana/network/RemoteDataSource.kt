package com.kfd.jobana.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    companion object {
        private const val BASE_URL = "http://192.168.0.102:8000/api/"
    }

    fun <Api> buildApi(api: Class<Api>) : Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    val login = HttpLoggingInterceptor()
                    login.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(login)
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api )

    }
}