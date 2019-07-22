package com.example.androidkotlinreactive.di.module

import android.content.Context
import com.example.androidkotlinreactive.extensions.clas
import com.example.androidkotlinreactive.network.CryptoNetworkService
import com.example.androidkotlinreactive.network.NetworkServices
import com.example.androidkotlinreactive.network.NetworkUtils
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class CryptoNetworkModule {

    companion object {
        const val CACHE_DIR_SIZE_30MB = 30 * 1024 * 1024
        const val KEEP_ALIVE_DURATION = (30 * 1000).toLong() // 30 seconds
        const val MAX_IDLE_CONNECTIONS = 10
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .registerTypeAdapter(clas<Date>(), DateTypeAdapter()).create()
    }

    @Provides
    @Singleton
    fun providesOkHttpLoggingInterceptor(context: Context): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = logLevel("NONE")
        }
    }

    private fun logLevel(level: String): HttpLoggingInterceptor.Level {
        return when (level) {
            "NONE" -> HttpLoggingInterceptor.Level.NONE
            "BASIC" -> HttpLoggingInterceptor.Level.BASIC
            "HEADERS" -> HttpLoggingInterceptor.Level.HEADERS
            "BODY" -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun providesCache(context: Context): Cache {
        return Cache(context.externalCacheDir!!, CACHE_DIR_SIZE_30MB.toLong())
    }

    @Provides
    @Singleton
    fun providesConnectionPool(): ConnectionPool {
        return ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.MICROSECONDS)
    }

    @Provides
    @Singleton
    fun providesOkHttp3Client(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        connectionPool: ConnectionPool
    ): OkHttpClient {
        val timeout = 20
        return OkHttpClient.Builder()
            .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .connectionPool(connectionPool)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(context: Context, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://api.coinmarketcap.com/")
            .build()
    }

    @Provides
    @Singleton
    fun providesNetworkUtiles(context: Context): NetworkUtils = NetworkUtils(context)

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): NetworkServices = retrofit.create(clas<NetworkServices>())

    @Provides
    @Singleton
    fun providesCryptoNetworkService(networkServices: NetworkServices) : CryptoNetworkService = CryptoNetworkService(networkServices)



}