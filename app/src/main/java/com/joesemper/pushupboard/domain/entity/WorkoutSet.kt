package com.joesemper.pushupboard.domain.entity

data class WorkoutSet(
    val workoutSetId: Int = 0,
    val workoutId: Int = 0,
    val exercise: WorkoutExercise,
    val exerciseReps: Int = 0,
    val exerciseRepsDone: Int = 0
)