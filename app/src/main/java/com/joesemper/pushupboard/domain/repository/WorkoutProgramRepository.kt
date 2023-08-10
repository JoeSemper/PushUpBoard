package com.joesemper.pushupboard.domain.repository

import com.joesemper.pushupboard.domain.entity.Program
import com.joesemper.pushupboard.domain.entity.Workout
import com.joesemper.pushupboard.domain.entity.WorkoutSet
import com.joesemper.pushupboard.domain.entity.WorkoutWithMuscleGroups
import kotlinx.coroutines.flow.Flow

interface WorkoutProgramRepository {
    suspend fun isEmpty(): Boolean
    suspend fun initialise()

    fun getAllPrograms(): Flow<List<Program>>
    fun getWorkoutsForProgram(programId: Int): Flow<List<WorkoutWithMuscleGroups>>
    fun getWorkoutSetsForWorkout(workoutId: Int): Flow<List<WorkoutSet>>
    fun getProgramById(programId: Int): Flow<Program>
    suspend fun getWorkoutSetById(workoutSetId: Int): WorkoutSet
    fun getWorkoutWithSetsById(workoutId: Int): Flow<Workout>
    suspend fun updateWorkoutCompleteStatus(workoutId: Int, isComplete: Boolean)
    suspend fun updateWorkoutSetRepsDone(workoutSetId: Int, repsDone: Int)

}