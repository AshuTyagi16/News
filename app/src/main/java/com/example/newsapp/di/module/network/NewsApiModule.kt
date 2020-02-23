package com.example.newsapp.di.module.network

import com.example.newsapp.data.network.NewsRepository
import com.example.newsapp.data.network.NewsService
import com.example.newsapp.di.scope.NewsAppScope
import dagger.Module
import dagger.Provides

@Module(includes = [NewsServiceModule::class])
class NewsApiModule {

    @Provides
    @NewsAppScope
    fun instaSolveApi(newsService: NewsService): NewsRepository {
        return NewsRepository(newsService)
    }
}