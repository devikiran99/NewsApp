package com.devikiran.newsy.features_components.detail.data.repository

import com.devikiran.newsy.features_components.detail.data.dao.DetailDao
import com.devikiran.newsy.features_components.detail.data.model.toDetailArticle
import com.devikiran.newsy.features_components.detail.domain.models.DetailArticle
import com.devikiran.newsy.features_components.detail.domain.repository.DetailRepository
import com.devikiran.newsy.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DetailRepositoryImpl(
    private val detailDao: DetailDao,
) : DetailRepository {
    override suspend fun getHeadlineArticleById(id: Int): Flow<Resource<DetailArticle>> {
        return callbackFlow {
            try {
                trySend(Resource.Loading())
                val headline = detailDao.getHeadlineArticleById(id).toDetailArticle()
                trySend(Resource.Success(data = headline))
            } catch (e: Exception) {
                trySend(Resource.Error(e))
                e.printStackTrace()
            }
            awaitClose { }
        }
    }

    override suspend fun getDiscoverArticleById(id: Int): Flow<Resource<DetailArticle>> {
        return callbackFlow {
            try {
                trySend(Resource.Loading())
                val discover = detailDao.getDiscoverArticleById(id).toDetailArticle()
                trySend(Resource.Success(data = discover))
            } catch (e: Exception) {
                trySend(Resource.Error(e))
            }
            awaitClose { }
        }
    }

    override suspend fun getSearchArticleById(id: Int): Flow<Resource<DetailArticle>> {
        return callbackFlow {
            try {
                trySend(Resource.Loading())
                val discover = detailDao.getSearchArticleById(id).toDetailArticle()
                trySend(Resource.Success(data = discover))
            } catch (e: Exception) {
                trySend(Resource.Error(e))
            }
            awaitClose { }
        }
    }
}