package com.joesemper.pushupboard.data.datasourse.room.prepopulated.dao

import androidx.room.Dao
import androidx.room.Query
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.*

@Dao
interface PrepopulatedProgramDao {

    @Query("SELECT * FROM Programs")
    suspend fun getAllPrograms(): List<PrepopulatedProgram>

    @Query("SELECT * FROM Exercises")
    suspend fun getAllWorkoutExercises(): List<PrepopulatedWorkoutExercise>

    @Query("SELECT * FROM MuscleGroups")
    suspend fun getAllMuscleGroups(): List<PrepopulatedMuscleGroup>

    @Query("SELECT * FROM Workouts")
    suspend fun getAllWorkouts(): List<PrepopulatedWorkout>

    @Query("SELECT * FROM WorkoutSets")
    suspend fun getAllWorkoutSets(): List<PrepopulatedWorkoutSet>

}