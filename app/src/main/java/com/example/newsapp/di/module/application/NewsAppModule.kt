package com.example.newsapp.di.module.application

import com.example.newsapp.di.scope.NewsAppScope
import dagger.Module
import dagger.Provides
import timber.log.Timber

@Module
class NewsAppModule {

    @Provides
    @NewsAppScope
    fun timberTree(): Timber.Tree {
        return Timber.DebugTree()
    }
}