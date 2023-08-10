package com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "Exercises",
    foreignKeys = [ForeignKey(
        entity = PrepopulatedMuscleGroup::class,
        parentColumns = arrayOf("muscle_group_id"),
        childColumns = arrayOf("muscle_group_id"),
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
@Parcelize
data class PrepopulatedWorkoutExercise(
    @PrimaryKey()
    @ColumnInfo(name = "exercise_id") val exerciseId: Int?,
    @ColumnInfo(name = "exercise_name") val exerciseName: String?,
    @ColumnInfo(name = "muscle_group_id") val muscleGroupId: Int?,
    @ColumnInfo(name = "description") val description: String?
) : Parcelable