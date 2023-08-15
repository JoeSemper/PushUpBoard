package com.joesemper.pushupboard.domain.use_case

import com.joesemper.pushupboard.data.datasourse.converters.getCurrentDateInMilliseconds
import com.joesemper.pushupboard.data.datasourse.converters.getDatesFromStart
import com.joesemper.pushupboard.domain.repository.WorkoutProgramRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateWorkoutDatesForProgramUseCase(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val workoutProgramRepository: WorkoutProgramRepository
) {
    suspend operator fun invoke(programId: Int) = withContext(defaultDispatcher) {
        val workoutsInProgram = workoutProgramRepository.getWorkoutsCountInProgram(programId)
        val newDates = getDatesFromStart(getCurrentDateInMilliseconds(), workoutsInProgram)
        workoutProgramRepository.updateDatesForProgram(programId, newDates)
    }
}