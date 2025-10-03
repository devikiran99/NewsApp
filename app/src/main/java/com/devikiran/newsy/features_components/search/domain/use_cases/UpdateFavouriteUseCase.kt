package com.devikiran.newsy.features_components.search.domain.use_cases

import com.devikiran.newsy.features_components.search.domain.models.SearchArticle
import com.devikiran.newsy.features_components.search.domain.repository.SearchRepository

class UpdateFavouriteUseCase(
    private val repository: SearchRepository,
) {
    suspend operator fun invoke(article: SearchArticle) {
        repository.updateFavouriteArticle(article)
    }
}