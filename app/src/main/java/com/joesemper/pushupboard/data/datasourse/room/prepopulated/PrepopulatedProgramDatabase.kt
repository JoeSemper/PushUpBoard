package com.joesemper.pushupboard.data.datasourse.room.prepopulated

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.dao.PrepopulatedProgramDao
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.*

@Database(
    entities = [
        PrepopulatedMuscleGroup::class,
        PrepopulatedProgram::class,
        PrepopulatedWorkoutSet::class,
        PrepopulatedWorkout::class,
        PrepopulatedWorkoutExercise::class
    ],
    version = 1
)
abstract class PrepopulatedProgramDatabase() : RoomDatabase() {
    abstract fun prepopulatedProgramDao(): PrepopulatedProgramDao
}