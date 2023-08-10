package com.joesemper.pushupboard.data.datasourse.room.main.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
class DatabaseWorkoutWithWorkoutSets(
    @Embedded
    val databaseWorkout: DatabaseWorkout,

    @Relation(
        entity = DatabaseWorkoutSet::class,
        parentColumn = "workout_id",
        entityColumn = "workout_id",
    )
    val databaseWorkoutSets: List<DatabaseWorkoutSetWithExercise>,
) : Parcelable

