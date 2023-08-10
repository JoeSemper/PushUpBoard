package com.joesemper.pushupboard.domain.use_case

import com.joesemper.pushupboard.domain.repository.SettingsDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetCurrentWorkoutProgramIdUseCase(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val settingsDataStore: SettingsDataStore,
    private val setWorkoutProgramSelectStatus: SetWorkoutProgramSelectStatusUseCase
) {
    suspend operator fun invoke(programId: Int) = withContext(defaultDispatcher) {
        settingsDataStore.setCurrentWorkoutProgram(programId)
        setWorkoutProgramSelectStatus(isSelected = true)
    }
}