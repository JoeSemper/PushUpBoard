package com.joesemper.pushupboard.data.datasourse.room.main.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedWorkout
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedWorkoutExercise
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "WorkoutSets",
    foreignKeys = [ForeignKey(
        entity = DatabaseWorkout::class,
        parentColumns = arrayOf("workout_id"),
        childColumns = arrayOf("workout_id"),
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.NO_ACTION
    ), ForeignKey(
        entity = DatabaseWorkoutExercise::class,
        parentColumns = arrayOf("exercise_id"),
        childColumns = arrayOf("exercise_id"),
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
@Parcelize
data class DatabaseWorkoutSet(
    @PrimaryKey()
    @ColumnInfo(name = "workout_set_id") val workoutSetId: Int = 0,
    @ColumnInfo(name = "workout_id") val workoutId: Int = 0,
    @ColumnInfo(name = "exercise_id") val exerciseId: Int = 0,
    @ColumnInfo(name = "exercise_reps") val exerciseReps: Int = 0,
    @ColumnInfo(name = "exercise_reps_done") val exerciseRepsDone: Int = 0
) : Parcelable