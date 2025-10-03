package com.devikiran.newsy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.devikiran.newsy.features_components.core.data.local.NewsyArticleDatabase
import com.devikiran.newsy.features_components.detail.data.dao.DetailDao
import com.devikiran.newsy.features_components.detail.data.repository.DetailRepositoryImpl
import com.devikiran.newsy.features_components.detail.domain.repository.DetailRepository
import com.devikiran.newsy.features_components.detail.domain.use_cases.DetailUseCases
import com.devikiran.newsy.features_components.detail.domain.use_cases.GetDetailDiscoverArticleUseCase
import com.devikiran.newsy.features_components.detail.domain.use_cases.GetDetailHeadlineArticleUseCase
import com.devikiran.newsy.features_components.detail.domain.use_cases.GetDetailSearchArticleUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailModule {
    @Provides
    @Singleton
    fun provideDetailRepository(
        detailDao: DetailDao,
    ): DetailRepository =
        DetailRepositoryImpl(detailDao)

    @Provides
    @Singleton
    fun provideDetailDao(
        newsyArticleDatabase: NewsyArticleDatabase,
    ): DetailDao = newsyArticleDatabase.detailDao()

    @Provides
    @Singleton
    fun provideDetailUsecases(repo: DetailRepository): DetailUseCases = DetailUseCases(
        getDetailDiscoverArticleUseCase = GetDetailDiscoverArticleUseCase(
            repo
        ),
        getDetailHeadlineArticleUseCase = GetDetailHeadlineArticleUseCase(
            repo
        ),
        getDetailSearchArticleUseCase = GetDetailSearchArticleUseCase(
            repo
        )

    )

}