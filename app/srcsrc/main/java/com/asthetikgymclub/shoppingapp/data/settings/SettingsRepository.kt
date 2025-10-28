package com.asthetikgymclub.shoppingapp.data.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun isDarkMode(): Flow<Boolean>
    suspend fun setDarkMode(isDarkMode: Boolean)
}
