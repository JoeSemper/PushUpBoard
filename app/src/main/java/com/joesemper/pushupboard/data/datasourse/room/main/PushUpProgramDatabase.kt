package com.joesemper.pushupboard.data.datasourse.room.main

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joesemper.pushupboard.data.datasourse.room.main.dao.WorkoutProgramDao
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseMuscleGroup
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseProgram
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseWorkout
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseWorkoutExercise
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseWorkoutSet


@Database(
    entities = [
        DatabaseMuscleGroup::class,
        DatabaseProgram::class,
        DatabaseWorkout::class,
        DatabaseWorkoutSet::class,
        DatabaseWorkoutExercise::class
    ],
    version = 1
)
abstract class PushUpBoardDatabase() : RoomDatabase() {
    abstract fun pushupboardDao(): WorkoutProgramDao
}