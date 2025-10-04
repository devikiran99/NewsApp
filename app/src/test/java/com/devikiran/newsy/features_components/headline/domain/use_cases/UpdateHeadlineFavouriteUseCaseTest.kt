package com.devikiran.newsy.features_components.headline.domain.use_cases

import androidx.paging.testing.asSnapshot
import com.devikiran.newsy.features_components.FakeHeadlineRepo
import com.devikiran.newsy.utils.Utils
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test


class UpdateHeadlineFavouriteUseCaseTest{
    private val repository = FakeHeadlineRepo()

    private val updateHeadlineFavouriteUseCase = UpdateHeadlineFavouriteUseCase(repository)

    @Test
    fun `updateHeadlineFavouriteUseCase update article correctly`() = runTest {
        //GIVEN
        val articleToUpdate = Utils.newsyArticles[0].copy(favourite = true)
        //WHEN
        updateHeadlineFavouriteUseCase.invoke(articleToUpdate)
        //THEN
        val updatedArticle = repository.fetchHeadlineArticle("a","b","c").asSnapshot().first()
        assertThat(updatedArticle.favourite).isTrue()

    }
}