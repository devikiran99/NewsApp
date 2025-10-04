package com.devikiran.newsy.features_components.headline.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import androidx.paging.testing.asPagingSourceFactory
import androidx.paging.testing.asSnapshot
import com.devikiran.newsy.MainDispatcherRule
import com.devikiran.newsy.features_components.core.domain.mapper.Mapper
import com.devikiran.newsy.features_components.core.domain.models.NewsyArticle
import com.devikiran.newsy.features_components.headline.data.local.dao.HeadlineDao
import com.devikiran.newsy.features_components.headline.data.local.model.HeadlineDto
import com.devikiran.newsy.features_components.headline.data.mapper.HeadlineMapper
import com.devikiran.newsy.features_components.headline.data.paging.HeadlineRemoteMediator
import com.devikiran.newsy.features_components.headline.domain.repository.HeadlineRepository
import com.devikiran.newsy.utils.Utils

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class HeadlineRepositoryImplTest {

    @get:Rule
    val mainCoroutineRule = MainDispatcherRule()

    @Mock
    lateinit var headlineDao: HeadlineDao

    @Mock
    lateinit var headlineRemoteMediator: HeadlineRemoteMediator

    private lateinit var mapper: Mapper<HeadlineDto, NewsyArticle>

    private lateinit var headlineRepository: HeadlineRepository

    @Before
    fun setUp() {
        mapper = HeadlineMapper()
        headlineRepository = HeadlineRepositoryImpl(
            mapper = mapper,
            headlineDao = headlineDao,
            mediator = headlineRemoteMediator
        )
    }

    @OptIn(ExperimentalPagingApi::class, ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchHeadlineArticle returns paginated Data with mapped Article`() = runTest {
        //GIVEN
        val mockMediatorResult =
            RemoteMediator.MediatorResult.Success(endOfPaginationReached = true)
        val pagingSource = Utils.headlineDto.asPagingSourceFactory().invoke()
        whenever(headlineDao.getAllHeadlineArticles()).thenReturn(pagingSource)
        whenever(headlineRemoteMediator.load(any(), any())).thenReturn(mockMediatorResult)
        //WHEN
        val pagingData: MutableList<NewsyArticle> = mutableListOf()
        val result = headlineRepository.fetchHeadlineArticle("sports", "us", "en")
        val job = launch(mainCoroutineRule.testDispatcher) {
            result.asSnapshot().toCollection(pagingData)
        }
        advanceUntilIdle()
        //THEN
        assertThat(pagingData).isNotEmpty()
        val newsyArticle = pagingData.first()
        assertThat(newsyArticle.title).isEqualTo("title")
    }

    @Test
    fun `fetchHeadlineArticle should apply parameters to mediator correctly`() {
        headlineRepository.fetchHeadlineArticle("sports", "us", "en")

        verify(headlineRemoteMediator).category = "sports"
        verify(headlineRemoteMediator).country = "us"
        verify(headlineRemoteMediator).language = "en"

    }

    @Test
    fun `updateFavouriteArticle updates the correct article`() = runTest {
        val newsyArticle = Utils.newsyArticles[0].copy(favourite = true)
        headlineRepository.updateFavouriteArticle(newsyArticle)
        verify(headlineDao).updateFavouriteArticle(
            isFavourite = true,
            id = 1
        )
    }
}