package com.example.ecoapp.network.responses

import com.example.ecoapp.network.model.ArticleDto
import com.google.gson.annotations.SerializedName

data class NewsSearchResponse(
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<ArticleDto>,
)