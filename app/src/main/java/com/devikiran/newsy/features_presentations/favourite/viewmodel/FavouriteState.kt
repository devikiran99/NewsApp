package com.devikiran.newsy.features_presentations.favourite.viewmodel

import androidx.paging.PagingData
import com.devikiran.newsy.features_components.favourite.domain.model.FavouriteArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FavouriteState(
    val favouriteArticles:Flow<PagingData<FavouriteArticle>> = emptyFlow()
)
