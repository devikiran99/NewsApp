package com.devikiran.newsy.features_components.detail.domain.use_cases

import com.devikiran.newsy.features_components.detail.domain.models.DetailArticle
import com.devikiran.newsy.features_components.detail.domain.repository.DetailRepository
import com.devikiran.newsy.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetDetailHeadlineArticleUseCase(
    private val detailRepository: DetailRepository,
) {
    suspend operator fun invoke(id: Int): Flow<Resource<DetailArticle>> =
        detailRepository.getHeadlineArticleById(id)
}