package com.example.newsapp

import com.example.newsapp.di.component.DaggerNewsAppComponent
import com.example.newsapp.di.component.NewsAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class NewsApp : DaggerApplication() {

    private lateinit var component: NewsAppComponent

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        if (!::component.isInitialized)
            initComponent()
        return component
    }

    private fun initTimber() {
        Timber.plant(component.timberTree())
    }

    private fun initComponent() {
        component = DaggerNewsAppComponent.factory().create(applicationContext)
    }
}