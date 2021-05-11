package com.example.ecoapp.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecoapp.R
import com.example.ecoapp.adapter.NewsItemAdapter
import com.example.ecoapp.model.NewsItem
import com.example.ecoapp.util.TAG
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = NewsItemAdapter(requireContext(),
            listOf(
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description"),
                NewsItem("title", "1", "description")
            )
        )
        news_recycler.adapter = adapter
        news_recycler.layoutManager = LinearLayoutManager(requireContext())
        Log.d(TAG, "onViewCreated: ${adapter}")
    }
}
