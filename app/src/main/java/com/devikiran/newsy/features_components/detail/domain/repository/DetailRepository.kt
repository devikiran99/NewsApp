package com.devikiran.newsy.features_components.detail.domain.repository

import com.devikiran.newsy.features_components.detail.domain.models.DetailArticle
import com.devikiran.newsy.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getHeadlineArticleById(id: Int): Flow<Resource<DetailArticle>>
    suspend fun getDiscoverArticleById(id: Int): Flow<Resource<DetailArticle>>
    suspend fun getSearchArticleById(id: Int): Flow<Resource<DetailArticle>>
}