package com.joesemper.pushupboard.di

import androidx.room.Room
import com.joesemper.pushupboard.data.datasourse.room.main.PushUpBoardDatabase
import com.joesemper.pushupboard.data.datasourse.room.main.dao.WorkoutProgramDao
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.PrepopulatedProgramDatabase
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.dao.PrepopulatedProgramDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<PushUpBoardDatabase> {
        Room.databaseBuilder(
            androidContext(),
            PushUpBoardDatabase::class.java,
            "PushUpBoardDatabase"
        )
            .build()
    }

    single<WorkoutProgramDao> {
        val database = get<PushUpBoardDatabase>()
        database.pushupboardDao()
    }


    factory <PrepopulatedProgramDatabase> {
        Room.databaseBuilder(
            androidContext(),
            PrepopulatedProgramDatabase::class.java,
            "PrepopulatedProgramDatabase"
        )
            .createFromAsset("database/pushUpProgramDatabase.db")
            .build()
    }

    factory <PrepopulatedProgramDao> {
        val prepopulatedDatabase = get<PrepopulatedProgramDatabase>()
        prepopulatedDatabase.prepopulatedProgramDao()
    }
}