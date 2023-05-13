package com.kfd.jobana.di

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.kfd.jobana.data.UserPreferences
import com.kfd.jobana.helpers.Constants
import com.kfd.jobana.network.AdvertApiService
import com.kfd.jobana.network.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context) = UserPreferences(context)

    @Provides
    @Singleton
    fun provideAuthApiService(BASE_URL: String) : AuthApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApiService::class.java)


    @Provides
    @Singleton
    fun provideAdvertApiService(BASE_URL: String, userPreferences: UserPreferences? = null) : AdvertApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            runBlocking {
                                val token = userPreferences?.authToken?.first()
                                Log.d(TAG, "TOKEN = ${token}")
                                it.addHeader("Authorization", "Bearer $token")
                            }
                        }.build())
                    }.also { client ->
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AdvertApiService::class.java)


}