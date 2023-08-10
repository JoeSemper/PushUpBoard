package com.joesemper.pushupboard.data.datasourse.room.main.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joesemper.pushupboard.domain.entity.Program
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "Programs"
)
@Parcelize
data class DatabaseProgram(
    @PrimaryKey()
    @ColumnInfo(name = "program_id") val programId: Int,
    @ColumnInfo(name = "program_name") val programName: String = "",
) : Parcelable