package com.joesemper.pushupboard.domain.use_case

import com.joesemper.pushupboard.domain.repository.WorkoutProgramRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InitiateDatabaseUseCase(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val workoutProgramRepository: WorkoutProgramRepository
) {
    suspend operator fun invoke() = withContext(defaultDispatcher) {
        if(workoutProgramRepository.isEmpty()) {
            workoutProgramRepository.initialise()
        }
    }
}