package com.example.newsapp.ui.news.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.newsapp.R
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var url: String

    companion object {
        private const val EXTRA_URL = "EXTRA_URL"
        fun newIntent(context: Context, url: String): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setStatusBarColor()
        getArguments()
        initWebView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    private fun setStatusBarColor() {
        StatusBarUtil.setColorNoTranslucent(
            this,
            ContextCompat.getColor(this, android.R.color.black)
        )
    }

    private fun getArguments() {
        intent?.let {
            it.getStringExtra(EXTRA_URL)?.let {
                url = it
            }
        }
    }

    private fun initWebView() {
        webView.settings?.let {
            it.javaScriptEnabled = true
            it.loadWithOverviewMode = true
            it.useWideViewPort = true
            it.builtInZoomControls = true
            it.displayZoomControls = false
        }
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }
        webView.loadUrl(url)
    }
}