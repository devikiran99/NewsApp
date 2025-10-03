package com.devikiran.newsy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.devikiran.newsy.features_components.core.data.local.NewsyArticleDatabase
import com.devikiran.newsy.features_components.core.domain.mapper.Mapper
import com.devikiran.newsy.features_components.search.data.local.models.SearchDto
import com.devikiran.newsy.features_components.search.data.mappers.SearchMapper
import com.devikiran.newsy.features_components.search.data.remote.SearchApi
import com.devikiran.newsy.features_components.search.data.repository.SearchRepositoryImpl
import com.devikiran.newsy.features_components.search.domain.models.SearchArticle
import com.devikiran.newsy.features_components.search.domain.repository.SearchRepository
import com.devikiran.newsy.features_components.search.domain.use_cases.FetchSearchArticleUseCase
import com.devikiran.newsy.features_components.search.domain.use_cases.SearchUseCases
import com.devikiran.newsy.features_components.search.domain.use_cases.UpdateFavouriteUseCase
import com.devikiran.newsy.utils.K
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideSearchMapper(): Mapper<SearchDto, SearchArticle> =
        SearchMapper()

    @Provides
    @Singleton
    fun provideSearchApi(): SearchApi {
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApi::class.java)
    }


    @Provides
    @Singleton
    fun provideSearchRepository(
        database: NewsyArticleDatabase,
        searchMapper: Mapper<SearchDto, SearchArticle>,
        searchApi: SearchApi,
    ): SearchRepository =
        SearchRepositoryImpl(
            api = searchApi,
            database = database,
            mapper = searchMapper
        )

    @Provides
    @Singleton
    fun provideSearchUseCases(
        repository: SearchRepository,
    ): SearchUseCases = SearchUseCases(
        fetchSearchArticleUseCase = FetchSearchArticleUseCase(repository),
        updateFavouriteUseCase = UpdateFavouriteUseCase(repository)
    )
}







