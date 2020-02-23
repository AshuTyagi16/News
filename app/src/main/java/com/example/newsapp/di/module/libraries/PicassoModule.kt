package com.example.newsapp.di.module.libraries

import android.content.Context
import com.example.newsapp.di.scope.NewsAppScope
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class PicassoModule {

    @Provides
    @NewsAppScope
    fun picasso(context: Context): Picasso {
        return Picasso.Builder(context)
            .build()
    }
}