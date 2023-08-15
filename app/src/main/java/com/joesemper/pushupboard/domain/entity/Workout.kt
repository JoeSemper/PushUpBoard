package com.joesemper.pushupboard.domain.entity

data class Workout(
    val workoutId: Int = 0,
    val programId: Int = 0,
    val date: String = "",
    val dayInProgram: Int = 0,
    val isComplete: Boolean = false,
    val workoutSets: List<WorkoutSet> = listOf()
)
