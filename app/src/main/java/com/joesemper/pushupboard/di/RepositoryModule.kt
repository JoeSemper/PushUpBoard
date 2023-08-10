package com.joesemper.pushupboard.di

import com.joesemper.pushupboard.data.datasourse.datastore.SettingsDataStoreImpl
import com.joesemper.pushupboard.data.repository.WorkoutProgramRepositoryImpl
import com.joesemper.pushupboard.domain.repository.SettingsDataStore
import com.joesemper.pushupboard.domain.repository.WorkoutProgramRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<WorkoutProgramRepository> { WorkoutProgramRepositoryImpl(get()) }
    single<SettingsDataStore> { SettingsDataStoreImpl(androidContext()) }
}