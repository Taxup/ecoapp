package com.example.ecoapp.network.model

import com.example.ecoapp.domain.model.Article
import com.example.ecoapp.domain.util.DomainMapper

class ArticleDtoMapper : DomainMapper<ArticleDto, Article> {
    override fun mapToDomainModel(model: ArticleDto): Article =
        Article(
            author = model.author,
            title = model.title,
            description = model.description,
            url = model.url,
            urlToImage = model.urlToImage,
            publishedAt = model.publishedAt,
            content = model.content,
        )

    fun toDomainList(initial: List<ArticleDto>): List<Article> {
        return initial.map { mapToDomainModel(it) }
    }
}