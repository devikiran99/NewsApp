package com.devikiran.newsy.features_components.discover.data.remote

import com.devikiran.newsy.features_components.core.data.remote.models.NewsyRemoteDto
import com.devikiran.newsy.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverApi {
    companion object {
        private const val DISCOVER_END_POINT = "/v2/top-headlines"
    }

    @GET(DISCOVER_END_POINT)
    suspend fun getDiscoverHeadlines(
        @Query("apiKey") key: String = K.API_KEY,
        @Query("category") category: String,
        @Query("country") country: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): NewsyRemoteDto





}








