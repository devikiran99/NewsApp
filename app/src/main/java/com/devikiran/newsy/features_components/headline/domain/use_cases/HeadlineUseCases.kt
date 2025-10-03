package com.devikiran.newsy.features_components.headline.domain.use_cases

data class HeadlineUseCases(
    val fetchHeadlineArticleUseCase: FetchHeadlineArticleUseCase,
    val updateHeadlineFavouriteUseCase: UpdateHeadlineFavouriteUseCase,
)