package com.bruno.juegos.entertainment.di


import com.bruno.juegos.entertainment.net.ApiService
import com.bruno.juegos.entertainment.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun weatherApiService(): ApiService {
        val converterFactory = GsonConverterFactory.create()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(converterFactory)

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val okHttpClient = OkHttpClient.Builder().apply {
                connectTimeout(5, TimeUnit.SECONDS)
                addInterceptor(httpLoggingInterceptor)
            }.build()
            retrofitBuilder.client(okHttpClient)
        }

        return retrofitBuilder.build().create(ApiService::class.java)
    }

}