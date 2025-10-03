package com.devikiran.newsy.features_components.favourite.domain.use_cases

import androidx.paging.PagingData
import com.devikiran.newsy.features_components.favourite.domain.model.FavouriteArticle
import com.devikiran.newsy.features_components.favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavouriteUseCase(
    private val repository: FavouriteRepository,
) {
    operator fun invoke(): Flow<PagingData<FavouriteArticle>> =
        repository.getAllFavouriteArticle
}