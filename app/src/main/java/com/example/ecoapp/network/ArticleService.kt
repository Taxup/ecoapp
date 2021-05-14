package com.example.ecoapp.network

import com.example.ecoapp.network.responses.NewsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("everything")
    suspend fun search(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
    ): NewsSearchResponse
}