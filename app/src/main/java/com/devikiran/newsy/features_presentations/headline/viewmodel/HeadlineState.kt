package com.devikiran.newsy.features_presentations.headline.viewmodel

import androidx.paging.PagingData
import com.devikiran.newsy.features_components.core.domain.models.NewsyArticle
import com.devikiran.newsy.features_components.core.domain.models.Setting
import com.devikiran.newsy.utils.ArticleCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HeadlineState(
    val headlineArticles: Flow<PagingData<NewsyArticle>> = emptyFlow(),
    val selectedHeadlineCategory: ArticleCategory = ArticleCategory.BUSINESS,
    val setting: Setting = Setting(0, 0),
)