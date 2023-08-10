package com.joesemper.pushupboard.data.datasourse.room.main.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "MuscleGroups"
)
@Parcelize
data class DatabaseMuscleGroup(
    @PrimaryKey()
    @ColumnInfo(name = "muscle_group_id") val muscleGroupId: Int = 0,
    @ColumnInfo(name = "muscle_group_name") val muscleGroupName: String = "",
    @ColumnInfo(name = "muscle_group_res_di") val muscleGroupResId: Int
) : Parcelable