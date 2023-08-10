package com.joesemper.pushupboard.domain.entity

data class WorkoutWithMuscleGroups(
    val workoutId: Int,
    val programId: Int,
    val date: Long,
    val dayInProgram: Int,
    val isComplete: Boolean,
    val muscleGroups: Set<MuscleGroup>
)