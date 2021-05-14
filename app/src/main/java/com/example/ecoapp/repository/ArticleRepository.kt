package com.example.ecoapp.repository

import com.example.ecoapp.domain.model.Article

interface ArticleRepository {

    suspend fun search(query: String, apiKey: String) : List<Article>


}