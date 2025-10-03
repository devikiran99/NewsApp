package com.devikiran.newsy.features_presentations.detail.viewmodel

import com.devikiran.newsy.features_components.detail.domain.models.DetailArticle
import com.devikiran.newsy.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class DetailState(
    val selectedDetailArticle: Flow<Resource<DetailArticle>> = emptyFlow(),
    val error: Boolean = false,
)
