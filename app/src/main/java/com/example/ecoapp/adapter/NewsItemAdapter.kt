package com.example.ecoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecoapp.R
import com.example.ecoapp.model.NewsItem

class NewsItemAdapter(private val newsList: List<NewsItem>) : RecyclerView.Adapter<NewsItemAdapter.NewsItemViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsItemViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.item_news, p0, false)
        return NewsItemViewHolder(itemView)
    }

    override fun onBindViewHolder(p0: NewsItemViewHolder, p1: Int) {
        val currentItem = newsList[p1]

        p0.titleView.text = currentItem.title
//        todo set image
        Glide
                .with(p0.imageView)
                .load(currentItem.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(p0.imageView)
        p0.descriptionView.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class NewsItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.news_title)
        val imageView: ImageView = itemView.findViewById(R.id.news_image)
        val descriptionView: TextView = itemView.findViewById(R.id.news_description)
    }
}