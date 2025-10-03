package com.devikiran.newsy.features_components.core.domain.use_cases

import com.devikiran.newsy.features_components.core.domain.models.Setting
import com.devikiran.newsy.features_components.core.domain.repository.SettingRepository

class InsertSettingUseCase(
    private val settingRepository: SettingRepository,
) {
    suspend operator fun invoke(setting: Setting) {
        settingRepository.insertSetting(setting)
    }
}