package com.devikiran.newsy.features_components.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devikiran.newsy.features_components.core.data.local.dao.SettingDao
import com.devikiran.newsy.features_components.core.data.local.models.SettingsDto
import com.devikiran.newsy.features_components.detail.data.dao.DetailDao
import com.devikiran.newsy.features_components.discover.data.local.dao.DiscoverArticleDao
import com.devikiran.newsy.features_components.discover.data.local.dao.DiscoverRemoteKeyDao
import com.devikiran.newsy.features_components.discover.data.local.models.DiscoverArticleDto
import com.devikiran.newsy.features_components.discover.data.local.models.DiscoverKeys
import com.devikiran.newsy.features_components.favourite.data.dao.FavouriteDao
import com.devikiran.newsy.features_components.headline.data.local.dao.HeadlineDao
import com.devikiran.newsy.features_components.headline.data.local.dao.HeadlineRemoteKeyDao
import com.devikiran.newsy.features_components.headline.data.local.model.HeadlineDto
import com.devikiran.newsy.features_components.headline.data.local.model.HeadlineRemoteKey
import com.devikiran.newsy.features_components.search.data.local.dao.SearchArticleDao
import com.devikiran.newsy.features_components.search.data.local.dao.SearchRemoteKeyDao
import com.devikiran.newsy.features_components.search.data.local.models.SearchDto
import com.devikiran.newsy.features_components.search.data.local.models.SearchRemoteKey

@Database(
    entities = [
        HeadlineDto::class,
        HeadlineRemoteKey::class,
        DiscoverArticleDto::class,
        DiscoverKeys::class,
        SearchDto::class,
        SearchRemoteKey::class,
        SettingsDto::class
    ],
    exportSchema = false,
    version = 1
)
abstract class NewsyArticleDatabase : RoomDatabase() {
    abstract fun headlineDao(): HeadlineDao
    abstract fun headlineRemoteDao(): HeadlineRemoteKeyDao
    abstract fun discoverArticleDao(): DiscoverArticleDao
    abstract fun discoverRemoteKeyDao(): DiscoverRemoteKeyDao
    abstract fun detailDao(): DetailDao
    abstract fun searchArticleDao(): SearchArticleDao
    abstract fun searchKeyDao(): SearchRemoteKeyDao
    abstract fun favouriteDao(): FavouriteDao
    abstract fun settingDao(): SettingDao
}