package com.example.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.Error
import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.Resource
import com.example.newsapp.data.network.NewsRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel(), NewsRepository.OnGetNewsListener {

    private val _newsLiveData = MutableLiveData<Resource<News>>()
    val newsLiveData: LiveData<Resource<News>>
        get() = _newsLiveData

    fun getNews() {
        _newsLiveData.postValue(Resource.loading())
        newsRepository.getNews(this)
    }

    override fun onGetNewsSuccess(news: News) {
        _newsLiveData.postValue(Resource.success(news))
    }

    override fun onGetNewsFailure(error: Error) {
        _newsLiveData.postValue(Resource.error(error))
    }
}