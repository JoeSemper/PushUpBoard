package com.joesemper.pushupboard.data.datasourse.room.main.entity

import androidx.room.ColumnInfo

data class DatabaseWorkoutWithMuscleGroups(
    @ColumnInfo(name = "workout_id") val workoutId: Int,
    @ColumnInfo(name = "day_in_week") val dayInWeek: Int = 0,
    @ColumnInfo(name = "program_id") val programId: Int = 0,
    @ColumnInfo(name = "muscle_group_name") val muscleGroupName: String = ""
)