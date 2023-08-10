package com.joesemper.pushupboard.domain.entity

data class WorkoutExercise(
    val exerciseId: Int,
    val exerciseName: String = "",
    val muscleGroupId: Int = 0,
    val muscleGroup: MuscleGroup = MuscleGroup(),
    val description: String = ""
)