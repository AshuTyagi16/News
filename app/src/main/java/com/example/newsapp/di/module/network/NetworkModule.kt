package com.example.newsapp.di.module.network

import com.example.newsapp.data.exception.NoConnectivityException
import com.example.newsapp.di.qualifier.NewsLoggingInterceptor
import com.example.newsapp.di.qualifier.NewsNetworkInterceptor
import com.example.newsapp.di.scope.NewsAppScope
import com.example.newsapp.util.NetworkUtil
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {

    companion object {
        private const val CONNECTION_TIMEOUT: Long = 60
        private const val WRITE_TIMEOUT: Long = 60
        private const val READ_TIMEOUT: Long = 60
    }

    @Provides
    @NewsAppScope
    fun okHttpClient(
        @NewsNetworkInterceptor networkInterceptor: Interceptor,
        @NewsLoggingInterceptor loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @NewsAppScope
    @NewsLoggingInterceptor
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.i(message)
            }
        })
        loggingInterceptor.redactHeader("x-auth-token")
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @NewsAppScope
    @NewsNetworkInterceptor
    fun networkInterceptor(networkUtil: NetworkUtil): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            if (!networkUtil.isOnline()) {
                throw NoConnectivityException()
            }
            return@Interceptor chain.proceed(request)
        }
    }

}