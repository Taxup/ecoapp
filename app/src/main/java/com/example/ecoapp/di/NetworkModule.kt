package com.example.ecoapp.di


import com.example.ecoapp.network.ArticleService
import com.example.ecoapp.network.model.ArticleDtoMapper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRecipeMapper(): ArticleDtoMapper {
        return ArticleDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRecipeService(): ArticleService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ArticleService::class.java)
    }

    @Singleton
    @Provides
    fun provideApiKey(): String {
        return "00906c37edd646b1b82b438b839769bf"
    }
}