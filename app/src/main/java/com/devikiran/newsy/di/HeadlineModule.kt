package com.devikiran.newsy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.devikiran.newsy.features_components.core.data.local.NewsyArticleDatabase
import com.devikiran.newsy.features_components.core.data.remote.models.Article
import com.devikiran.newsy.features_components.core.domain.mapper.Mapper
import com.devikiran.newsy.features_components.core.domain.models.NewsyArticle
import com.devikiran.newsy.features_components.headline.data.local.dao.HeadlineDao
import com.devikiran.newsy.features_components.headline.data.local.dao.HeadlineRemoteKeyDao
import com.devikiran.newsy.features_components.headline.data.local.model.HeadlineDto
import com.devikiran.newsy.features_components.headline.data.mapper.ArticleHeadlineDtoMapper
import com.devikiran.newsy.features_components.headline.data.mapper.HeadlineMapper
import com.devikiran.newsy.features_components.headline.data.paging.HeadlineRemoteMediator
import com.devikiran.newsy.features_components.headline.data.remote.HeadlineApi
import com.devikiran.newsy.features_components.headline.data.repository.HeadlineRepositoryImpl
import com.devikiran.newsy.features_components.headline.domain.repository.HeadlineRepository
import com.devikiran.newsy.features_components.headline.domain.use_cases.FetchHeadlineArticleUseCase
import com.devikiran.newsy.features_components.headline.domain.use_cases.HeadlineUseCases
import com.devikiran.newsy.features_components.headline.domain.use_cases.UpdateHeadlineFavouriteUseCase
import com.devikiran.newsy.utils.K
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeadlineModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideHeadlineApi(): HeadlineApi {
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeadlineApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHeadlineMediator(
        api: HeadlineApi,
        database: NewsyArticleDatabase
    ): HeadlineRemoteMediator {
        return HeadlineRemoteMediator(
            api, database
        )
    }


    @Provides
    @Singleton
    fun provideHeadlineRepository(
        headlineDao: HeadlineDao,
        headlineRemoteMediator: HeadlineRemoteMediator,
        mapper: Mapper<HeadlineDto, NewsyArticle>,
    ): HeadlineRepository {
        return HeadlineRepositoryImpl(
            mediator = headlineRemoteMediator,
            headlineDao = headlineDao,
            mapper = mapper
        )
    }

    @Provides
    @Singleton
    fun provideHeadlineDao(
        database: NewsyArticleDatabase,
    ): HeadlineDao = database.headlineDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(
        database: NewsyArticleDatabase,
    ): HeadlineRemoteKeyDao = database.headlineRemoteDao()

    @Provides
    @Singleton
    fun provideHeadlineUseCases(
        repository: HeadlineRepository,
    ): HeadlineUseCases =
        HeadlineUseCases(
            fetchHeadlineArticleUseCase = FetchHeadlineArticleUseCase(
                repository = repository
            ),
            updateHeadlineFavouriteUseCase = UpdateHeadlineFavouriteUseCase(
                repository = repository
            )
        )

    @Provides
    @Singleton
    fun provideArticleToHeadlineMapper(): Mapper<Article, HeadlineDto> = ArticleHeadlineDtoMapper()

    @Provides
    @Singleton
    fun provideHeadlineMapper(): Mapper<HeadlineDto, NewsyArticle> = HeadlineMapper()

}