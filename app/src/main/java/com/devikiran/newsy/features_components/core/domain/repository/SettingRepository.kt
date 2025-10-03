package com.devikiran.newsy.features_components.core.domain.repository

import com.devikiran.newsy.features_components.core.domain.models.Setting
import com.devikiran.newsy.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    suspend fun getSetting(): Flow<Resource<Setting>>
    suspend fun insertSetting(setting: Setting)
}