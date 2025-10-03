package com.devikiran.newsy.features_components.headline.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.devikiran.newsy.features_components.core.domain.mapper.Mapper
import com.devikiran.newsy.features_components.core.domain.models.NewsyArticle
import com.devikiran.newsy.features_components.headline.data.local.dao.HeadlineDao
import com.devikiran.newsy.features_components.headline.data.local.model.HeadlineDto
import com.devikiran.newsy.features_components.headline.data.paging.HeadlineRemoteMediator
import com.devikiran.newsy.features_components.headline.domain.repository.HeadlineRepository
import com.devikiran.newsy.utils.K
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HeadlineRepositoryImpl(
    private val headlineDao: HeadlineDao,
    private val mediator: HeadlineRemoteMediator,
    private val mapper: Mapper<HeadlineDto, NewsyArticle>,
) : HeadlineRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchHeadlineArticle(
        category: String,
        country: String,
        language: String,
    ): Flow<PagingData<NewsyArticle>> {
        return Pager(
            PagingConfig(
                pageSize = K.PAGE_SIZE,
                prefetchDistance = K.PAGE_SIZE - 1,
                initialLoadSize = 10
            ),
            remoteMediator = mediator.apply {
                this.category = category
                this.country = country
                this.language = language
            }
        ) {
            headlineDao.getAllHeadlineArticles()
        }.flow.map { dtoPager ->
            dtoPager.map { dto ->
                mapper.toModel(dto)
            }
        }
    }

    override suspend fun updateFavouriteArticle(newsyArticle: NewsyArticle) {
        headlineDao.updateFavouriteArticle(
            isFavourite = newsyArticle.favourite,
            id = newsyArticle.id
        )
    }
}