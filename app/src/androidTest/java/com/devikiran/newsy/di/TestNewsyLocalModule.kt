package com.devikiran.newsy.di

import android.content.Context
import androidx.room.Room
import com.devikiran.newsy.features_components.core.data.local.NewsyArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NewsyLocalModule::class]
)
object TestNewsyLocalModule {

    @Singleton
    @Provides
    fun provideNewsyDatabase(
        @ApplicationContext context: Context,
    ): NewsyArticleDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            NewsyArticleDatabase::class.java,
        ).allowMainThreadQueries()
            .build()
    }

}