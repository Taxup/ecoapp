package com.example.ecoapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecoapp.R
import com.example.ecoapp.model.NewsItem
import com.example.ecoapp.util.TAG

class NewsItemAdapter(
    private val context: Context,
    private val newsList: List<NewsItem>
) : RecyclerView.Adapter<NewsItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onBindViewHolder: ")
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = newsList[position]

        Log.d(TAG, "onBindViewHolder: $position")
        holder.titleView.text = currentItem.title
//        todo set image
        Glide
            .with(context)
            .load(currentItem.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imageView)
        holder.descriptionView.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var titleView: TextView = itemView.findViewById(R.id.news_title)
        var imageView: ImageView = itemView.findViewById(R.id.news_image)
        var descriptionView: TextView = itemView.findViewById(R.id.news_description)
    }
}