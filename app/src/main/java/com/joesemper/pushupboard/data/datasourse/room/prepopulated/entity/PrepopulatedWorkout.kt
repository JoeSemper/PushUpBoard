package com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "Workouts",
    foreignKeys = [ForeignKey(
        entity = PrepopulatedProgram::class,
        parentColumns = arrayOf("program_id"),
        childColumns = arrayOf("program_id"),
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
@Parcelize
data class PrepopulatedWorkout(
    @PrimaryKey()
    @ColumnInfo(name = "workout_id") val workoutId: Int?,
    @ColumnInfo(name = "day_in_program") val dayInProgram: Int?,
    @ColumnInfo(name = "program_id") val programId: Int?,
) : Parcelable
