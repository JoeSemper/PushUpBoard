package com.joesemper.pushupboard.domain.use_case

import com.joesemper.pushupboard.domain.entity.WorkoutSet
import com.joesemper.pushupboard.domain.repository.WorkoutProgramRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.withContext

class UpdateWorkoutCompleteStatusUseCase(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val workoutProgramRepository: WorkoutProgramRepository
) {
    suspend operator fun invoke(workoutId: Int, isComplete: Boolean) =
        withContext(defaultDispatcher) {
            workoutProgramRepository.updateWorkoutCompleteStatus(
                workoutId = workoutId,
                isComplete = isComplete
            )
        }
}