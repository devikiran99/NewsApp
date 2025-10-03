package com.devikiran.newsy.features_components.search.domain.use_cases

import androidx.paging.PagingData
import com.devikiran.newsy.features_components.search.domain.models.SearchArticle
import com.devikiran.newsy.features_components.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class FetchSearchArticleUseCase(
    private val repository: SearchRepository,
) {
    operator fun invoke(query: String): Flow<PagingData<SearchArticle>> =
        repository.fetchSearchArticles(query)
}