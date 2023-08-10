package com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "WorkoutSets",
    foreignKeys = [ForeignKey(
        entity = PrepopulatedWorkout::class,
        parentColumns = arrayOf("workout_id"),
        childColumns = arrayOf("workout_id"),
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.NO_ACTION
    ), ForeignKey(
        entity = PrepopulatedWorkoutExercise::class,
        parentColumns = arrayOf("exercise_id"),
        childColumns = arrayOf("exercise_id"),
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
@Parcelize
data class PrepopulatedWorkoutSet(
    @PrimaryKey
    @ColumnInfo(name = "workout_set_id") val workoutSetId: Int?,
    @ColumnInfo(name = "workout_id") val workoutId: Int?,
    @ColumnInfo(name = "exercise_id") val exerciseId: Int?,
    @ColumnInfo(name = "exercise_reps") val exerciseReps: Int?
) : Parcelable