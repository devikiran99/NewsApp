package com.devikiran.newsy.features_presentations.home.viewmodel

import com.devikiran.newsy.features_components.core.domain.models.NewsyArticle
import com.devikiran.newsy.utils.ArticleCategory

sealed class HomeUIEvents{
    object ViewMoreClicked:HomeUIEvents()
    data class ArticleClicked(val url:String):HomeUIEvents()
    data class CategoryChange(val category: ArticleCategory) : HomeUIEvents()
    data class PreferencePanelToggle(val isOpen: Boolean) : HomeUIEvents()
    data class OnHeadLineFavouriteChange(val article: NewsyArticle) : HomeUIEvents()
    data class OnDiscoverFavouriteChange(val article: NewsyArticle) : HomeUIEvents()
}