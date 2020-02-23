package com.example.newsapp.di.component

import android.content.Context
import com.example.newsapp.*
import com.example.newsapp.di.module.activity.ActivityBindingModule
import com.example.newsapp.di.module.application.NewsAppModule
import com.example.newsapp.di.module.libraries.PicassoModule
import com.example.newsapp.di.module.network.NewsApiModule
import com.example.newsapp.di.module.util.UtilsModule
import com.example.newsapp.di.module.util.ViewModelFactoryModule
import com.example.newsapp.di.scope.NewsAppScope
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import timber.log.Timber

@NewsAppScope
@Component(
    modules = [
        NewsAppModule::class,
        NewsApiModule::class,
        PicassoModule::class,
        UtilsModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        ActivityBindingModule::class
    ]
)
interface NewsAppComponent : AndroidInjector<NewsApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): NewsAppComponent
    }

    fun context(): Context

    fun timberTree(): Timber.Tree

    fun getPicasso(): Picasso

    fun getGson(): Gson
}