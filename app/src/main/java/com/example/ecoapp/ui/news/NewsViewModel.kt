package com.example.ecoapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecoapp.domain.model.Article
import com.example.ecoapp.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(
    private val repository: ArticleRepository,
    private val apiKey: String
): ViewModel() {

    val articles: MutableLiveData<List<Article>> = MutableLiveData(ArrayList())
    val query = MutableLiveData("usa")

    init {
        newSearch("usa")
    }

    fun newSearch(query: String = "") {
        viewModelScope.launch {
            val result = repository.search(query, apiKey)
            articles.value = result
        }
        this.query.value = query
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }
}