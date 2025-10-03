package com.devikiran.newsy.features_presentations

import com.devikiran.newsy.features_components.core.domain.models.Setting
import com.devikiran.newsy.features_components.core.domain.repository.SettingRepository
import com.devikiran.newsy.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSettingRepository : SettingRepository {
    private val settingFlow = MutableStateFlow<Resource<Setting>>(
        Resource.Success(Setting(0, 0))
    )

    override suspend fun getSetting(): Flow<Resource<Setting>> {
        return settingFlow
    }

    override suspend fun insertSetting(setting: Setting) {
        settingFlow.value = Resource.Success(setting)
    }
}