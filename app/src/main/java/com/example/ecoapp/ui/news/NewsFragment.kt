package com.example.ecoapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecoapp.R
import com.example.ecoapp.adapter.ArticleListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModels()

    private lateinit var adapter: ArticleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        setupViewModel()
    }

    private fun setupViews() {
        adapter = ArticleListAdapter(requireActivity(), listOf())
        news_recycler.adapter = adapter
        news_recycler.layoutManager = LinearLayoutManager(requireContext())
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.newSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.onQueryChanged(newText)
                }
                return false
            }
        })
        news_fab.setOnClickListener {
            //todo add new article
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            articles.observe(viewLifecycleOwner) {
                adapter.setArticles(it)
            }
        }
    }
}
