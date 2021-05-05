package com.example.ecoapp.ui.news

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoapp.R
import com.example.ecoapp.adapter.NewsItemAdapter
import com.example.ecoapp.model.NewsItem
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = news_recycler
        recyclerView.adapter = NewsItemAdapter(
                listOf(
                        NewsItem("1", "1", "1"),
                        NewsItem("1", "1", "1"),
                        NewsItem("1", "1", "1")
                )
        )
    }
}
