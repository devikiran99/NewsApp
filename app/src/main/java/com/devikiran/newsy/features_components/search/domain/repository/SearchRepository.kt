package com.devikiran.newsy.features_components.search.domain.repository

import androidx.paging.PagingData
import com.devikiran.newsy.features_components.search.domain.models.SearchArticle
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun fetchSearchArticles(
        query: String,
    ): Flow<PagingData<SearchArticle>>

    suspend fun updateFavouriteArticle(article: SearchArticle)
}