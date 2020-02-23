package com.example.newsapp.di.module.util

import android.content.Context
import com.example.newsapp.di.scope.NewsAppScope
import com.example.newsapp.util.NetworkUtil
import dagger.Module
import dagger.Provides

@Module(includes = [])
class UtilsModule {

    @Provides
    @NewsAppScope
    fun networkUtil(context: Context): NetworkUtil {
        return NetworkUtil(context)
    }
}