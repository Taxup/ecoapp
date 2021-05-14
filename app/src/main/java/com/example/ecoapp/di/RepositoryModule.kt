package com.example.ecoapp.di

import com.example.ecoapp.network.ArticleService
import com.example.ecoapp.network.model.ArticleDtoMapper
import com.example.ecoapp.repository.ArticleRepository
import com.example.ecoapp.repository.ArticleRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideArticleRepository(
        articleService: ArticleService,
        articleDtoMapper: ArticleDtoMapper
    ): ArticleRepository {
        return ArticleRepository_Impl(articleService, articleDtoMapper)
    }
}