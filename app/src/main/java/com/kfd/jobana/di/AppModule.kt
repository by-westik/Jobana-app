package com.kfd.jobana.di

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.kfd.jobana.data.UserPreferences
import com.kfd.jobana.helpers.Constants
import com.kfd.jobana.network.AdvertApiService
import com.kfd.jobana.network.AttachmentApiService
import com.kfd.jobana.network.AuthApiService
import com.kfd.jobana.network.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthOkhttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context) = UserPreferences(context)

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideAuthInterceptor(userPreferences: UserPreferences? = null): Interceptor {
        return Interceptor {
            it.proceed(it.request().newBuilder().also {
                runBlocking {
                    val token = userPreferences?.authToken?.first()
                    it.addHeader("Authorization", "Bearer $token")
                }
            }.build())
        }
    }


    @Provides
    @LoggingOkHttpClient
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
        return okHttpClient.build()
    }


    @Provides
    @AuthOkhttpClient
    fun provideAuthOkhttpClient(loggingInterceptor: HttpLoggingInterceptor, authInterceptor: Interceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
        return okHttpClient.build()
    }
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    //TODO сделать фунцкию для provide token and interceptor
    @Provides
    @Singleton
    fun provideAuthApiService(
        BASE_URL: String,
        @LoggingOkHttpClient client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) : AuthApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(AuthApiService::class.java)


    @Provides
    @Singleton
    fun provideAdvertApiService(
        BASE_URL: String,
        @AuthOkhttpClient client: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ) : AdvertApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(AdvertApiService::class.java)


    @Provides
    @Singleton
    fun provideUserApiService(
        BASE_URL: String,
        @AuthOkhttpClient client: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ) : UserApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(UserApiService::class.java)



    @Provides
    @Singleton
    fun provideAttachmentApiService(
        BASE_URL: String,
        @AuthOkhttpClient client: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ) : AttachmentApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(AttachmentApiService::class.java)


}