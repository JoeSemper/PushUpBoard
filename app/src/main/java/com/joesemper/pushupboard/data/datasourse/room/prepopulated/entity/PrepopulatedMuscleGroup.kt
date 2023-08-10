package com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "MuscleGroups"
)
@Parcelize
data class PrepopulatedMuscleGroup(
    @PrimaryKey()
    @ColumnInfo(name = "muscle_group_id") val muscleGroupId: Int?,
    @ColumnInfo(name = "muscle_group_name") val muscleGroupName: String?,
) : Parcelable