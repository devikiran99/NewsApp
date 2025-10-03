package com.devikiran.newsy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.devikiran.newsy.features_components.core.data.local.NewsyArticleDatabase
import com.devikiran.newsy.features_components.core.domain.mapper.Mapper
import com.devikiran.newsy.features_components.core.domain.models.NewsyArticle
import com.devikiran.newsy.features_components.discover.data.local.dao.DiscoverArticleDao
import com.devikiran.newsy.features_components.discover.data.local.dao.DiscoverRemoteKeyDao
import com.devikiran.newsy.features_components.discover.data.local.models.DiscoverArticleDto
import com.devikiran.newsy.features_components.discover.data.mapper.DiscoverMapper
import com.devikiran.newsy.features_components.discover.data.remote.DiscoverApi
import com.devikiran.newsy.features_components.discover.data.repository.DiscoverRepoImpl
import com.devikiran.newsy.features_components.discover.domain.repository.DiscoverRepository
import com.devikiran.newsy.features_components.discover.domain.use_cases.DiscoverUseCases
import com.devikiran.newsy.features_components.discover.domain.use_cases.FetchDiscoverArticleUseCase
import com.devikiran.newsy.features_components.discover.domain.use_cases.GetDiscoverCurrentCategoryUseCase
import com.devikiran.newsy.features_components.discover.domain.use_cases.UpdateCurrentCategoryUseCase
import com.devikiran.newsy.features_components.discover.domain.use_cases.UpdateFavouriteDiscoverArticleUseCase
import com.devikiran.newsy.utils.K
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiscoverModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideDiscoverApi(): DiscoverApi {
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiscoverApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDiscoverRepository(
        api: DiscoverApi,
        database: NewsyArticleDatabase,
        mapper: Mapper<DiscoverArticleDto, NewsyArticle>,
    ): DiscoverRepository {
        return DiscoverRepoImpl(
            discoverApi = api,
            database = database,
            mapper = mapper
        )
    }

    @Provides
    @Singleton
    fun provideDiscoverArticleDao(
        database: NewsyArticleDatabase,
    ): DiscoverArticleDao = database.discoverArticleDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(database: NewsyArticleDatabase): DiscoverRemoteKeyDao =
        database.discoverRemoteKeyDao()

    @Provides
    @Singleton
    fun provideDiscoverMapper(): Mapper<DiscoverArticleDto, NewsyArticle> =
        DiscoverMapper()

    @Provides
    @Singleton
    fun provideDiscoverUseCases(repository: DiscoverRepository): DiscoverUseCases {
        return DiscoverUseCases(
            fetchDiscoverArticleUseCase = FetchDiscoverArticleUseCase(repository),
            updateCurrentCategoryUseCase = UpdateCurrentCategoryUseCase(repository),
            getDiscoverCurrentCategoryUseCase = GetDiscoverCurrentCategoryUseCase(repository),
            updateFavouriteDiscoverArticleUseCase = UpdateFavouriteDiscoverArticleUseCase(repository)
        )
    }

}












