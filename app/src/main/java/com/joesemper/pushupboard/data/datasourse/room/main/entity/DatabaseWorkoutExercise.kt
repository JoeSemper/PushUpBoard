package com.joesemper.pushupboard.data.datasourse.room.main.entity

import android.os.Parcelable
import androidx.room.*
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedMuscleGroup
import com.joesemper.pushupboard.domain.entity.MuscleGroup
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "Exercises",
    foreignKeys = [ForeignKey(
        entity = DatabaseMuscleGroup::class,
        parentColumns = arrayOf("muscle_group_id"),
        childColumns = arrayOf("muscle_group_id"),
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
@Parcelize
data class DatabaseWorkoutExercise(
    @PrimaryKey()
    @ColumnInfo(name = "exercise_id") val exerciseId: Int,
    @ColumnInfo(name = "exercise_name") val exerciseName: String = "",
    @ColumnInfo(name = "muscle_group_id") val muscleGroupId: Int = 0,
    @ColumnInfo(name = "description") val description: String = ""

) : Parcelable