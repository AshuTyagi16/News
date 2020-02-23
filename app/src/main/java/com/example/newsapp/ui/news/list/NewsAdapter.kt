package com.example.newsapp.ui.news.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.model.Article
import com.squareup.picasso.Picasso

class NewsAdapter(private val picasso: Picasso) : RecyclerView.Adapter<NewsViewHolder>(),
    NewsViewHolder.OnClickListener {

    private lateinit var list: List<Article>
    private lateinit var onClickListener: OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (::list.isInitialized) list.size else 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (::list.isInitialized) {
            holder.setNews(list[position], picasso)
            holder.setOnClickListener(this)
        }
    }

    fun setList(list: List<Article>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onClick(position: Int, url: String?) {
        if (::onClickListener.isInitialized)
            onClickListener.onClick(position, url)
    }

    interface OnClickListener {
        fun onClick(position: Int, url: String?)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
}