package com.joesemper.pushupboard.domain.use_case

import com.joesemper.pushupboard.domain.repository.SettingsDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetWorkoutProgramSelectStatusUseCase(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val settingsDataStore: SettingsDataStore
) {
    suspend operator fun invoke() = withContext(defaultDispatcher) {
        settingsDataStore.isWorkoutProgramSelected()
    }
}
