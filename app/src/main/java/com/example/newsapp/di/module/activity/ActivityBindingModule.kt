package com.example.newsapp.di.module.activity

import com.example.newsapp.di.module.libraries.PicassoModule
import com.example.newsapp.di.scope.PerActivityScope
import com.example.newsapp.ui.news.list.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [PicassoModule::class])
abstract class ActivityBindingModule {

    @PerActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivity(): MainActivity
}