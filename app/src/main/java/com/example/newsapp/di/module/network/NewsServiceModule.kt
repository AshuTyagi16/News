package com.example.newsapp.di.module.network

import com.example.newsapp.data.network.NewsService
import com.example.newsapp.di.scope.NewsAppScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule::class])
class NewsServiceModule {

    companion object {
        private const val BASE_URL_RELEASE = "https://newsapi.org/v2/"
        private const val BASE_URL = BASE_URL_RELEASE
    }

    @Provides
    @NewsAppScope
    fun instaSolvService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    @NewsAppScope
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @NewsAppScope
    fun gson(): Gson {
        return GsonBuilder().create()
    }
}