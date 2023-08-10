package com.joesemper.pushupboard.data.datasourse.room.main.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatabaseWorkoutExerciseWithMuscleGroup(
    @Embedded
    val databaseWorkoutExercise: DatabaseWorkoutExercise,

    @Relation(
        parentColumn = "muscle_group_id",
        entityColumn = "muscle_group_id",
    )
    val databaseMuscleGroup: DatabaseMuscleGroup

) : Parcelable
