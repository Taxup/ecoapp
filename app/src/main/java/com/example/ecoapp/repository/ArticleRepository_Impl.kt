package com.example.ecoapp.repository

import com.example.ecoapp.domain.model.Article
import com.example.ecoapp.network.ArticleService
import com.example.ecoapp.network.model.ArticleDtoMapper

class ArticleRepository_Impl(
    val articleService: ArticleService,
    val mapper: ArticleDtoMapper
) : ArticleRepository {
    override suspend fun search(query: String, apiKey: String): List<Article> {
        return mapper.toDomainList(articleService.search(query, apiKey).articles)
    }
}