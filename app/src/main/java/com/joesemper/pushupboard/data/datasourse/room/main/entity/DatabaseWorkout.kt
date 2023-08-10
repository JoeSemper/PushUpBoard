package com.joesemper.pushupboard.data.datasourse.room.main.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedProgram
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "Workouts",
    foreignKeys = [ForeignKey(
        entity = DatabaseProgram::class,
        parentColumns = arrayOf("program_id"),
        childColumns = arrayOf("program_id"),
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
@Parcelize
data class DatabaseWorkout(
    @PrimaryKey()
    @ColumnInfo(name = "workout_id") val workoutId: Int,
    @ColumnInfo(name = "date") val date: Long = 0,
    @ColumnInfo(name = "day_in_program") val dayInProgram: Int = 0,
    @ColumnInfo(name = "program_id") val programId: Int = 0,
    @ColumnInfo(name = "is_complete") val isComplete: Boolean = false
) : Parcelable