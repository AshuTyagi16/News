package com.example.newsapp.di.module.activity

import androidx.lifecycle.ViewModel
import com.example.newsapp.di.mapkey.ViewModelKey
import com.example.newsapp.di.scope.PerActivityScope
import com.example.newsapp.ui.MainActivityViewModel
import com.example.newsapp.ui.news.list.NewsAdapter
import com.squareup.picasso.Picasso
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    companion object {
        @Provides
        @PerActivityScope
        fun newsAdapter(picasso: Picasso): NewsAdapter {
            return NewsAdapter(picasso)
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

}