package com.example.newsapp.ui.news.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.model.Status
import com.example.newsapp.ui.MainActivityViewModel
import com.example.newsapp.ui.news.detail.NewsDetailActivity
import com.example.newsapp.util.Constants
import com.jaeger.library.StatusBarUtil
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NewsAdapter.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var picasso: Picasso

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBarColor()
        inject()
        initializeWeather()
        initializeRecyclerView()
        getData()
        observeLiveData()
    }

    private fun setStatusBarColor() {
        StatusBarUtil.setColorNoTranslucent(
            this,
            ContextCompat.getColor(this, android.R.color.black)
        )
    }

    private fun inject() {
        mainActivityViewModel =
            ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    private fun initializeWeather() {
        picasso.load(Constants.WEATHER_LOGO)
            .error(R.drawable.ic_weather_placeholder)
            .placeholder(R.drawable.ic_weather_placeholder)
            .into(ivWeather)
    }

    private fun initializeRecyclerView() {
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = newsAdapter
        newsAdapter.setOnClickListener(this)
    }

    private fun getData() {
        mainActivityViewModel.getNews()
    }

    private fun observeLiveData() {
        mainActivityViewModel.newsLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    rvNews.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    it.data?.let {
                        progressBar.visibility = View.GONE
                        rvNews.visibility = View.VISIBLE
                        newsAdapter.setList(it.articles)
                    }
                }
                Status.ERROR -> {
                    rvNews.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    Toasty.error(this, "${it.message}", Toasty.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onClick(position: Int, url: String?) {
        url?.let {
            startActivity(NewsDetailActivity.newIntent(this, url))
        }
    }
}
