package com.example.newsapp.data.network

import com.example.newsapp.data.model.Error
import com.example.newsapp.data.model.News
import com.example.newsapp.util.ApiCallback
import com.example.newsapp.util.Constants

class NewsRepository(private val newsService: NewsService) {

    fun getNews(onGetNewsListener: OnGetNewsListener) {
        newsService.getNews(Constants.country, Constants.apikey, Constants.pageSize)
            .enqueue(object : ApiCallback<News>() {
                override fun success(response: News) {
                    onGetNewsListener.onGetNewsSuccess(response)
                }

                override fun failure(error: Error) {
                    onGetNewsListener.onGetNewsFailure(error)
                }

            })
    }

    interface OnGetNewsListener {
        fun onGetNewsSuccess(news: News)
        fun onGetNewsFailure(error: Error)
    }

}
