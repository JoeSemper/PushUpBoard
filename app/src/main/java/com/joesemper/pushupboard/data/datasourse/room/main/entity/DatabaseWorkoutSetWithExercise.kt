package com.joesemper.pushupboard.data.datasourse.room.main.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedWorkout
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedWorkoutExercise
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatabaseWorkoutSetWithExercise(
    @Embedded
    val databaseWorkoutSet: DatabaseWorkoutSet,

    @Relation(
        entity = DatabaseWorkoutExercise::class,
        parentColumn = "exercise_id",
        entityColumn = "exercise_id",
    )
    val databaseWorkoutExercise: DatabaseWorkoutExerciseWithMuscleGroup,

) : Parcelable