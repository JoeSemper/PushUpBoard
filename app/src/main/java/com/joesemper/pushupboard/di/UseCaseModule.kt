package com.joesemper.pushupboard.di

import com.joesemper.pushupboard.domain.use_case.*
import org.koin.androidx.compose.get
import org.koin.dsl.module

val useCaseModule = module {

    factory { InitiateDatabaseUseCase(workoutProgramRepository = get()) }
    factory { GetAllWorkoutProgramsUseCase(workoutProgramRepository = get()) }
    factory { GetWorkoutSetsForWorkoutUseCase(workoutProgramRepository = get()) }
    factory { GetWorkoutsForProgramUseCase(workoutProgramRepository = get()) }
    factory { GetWorkoutProgramByIdUseCase(workoutProgramRepository = get()) }
    factory { GetWorkoutByIdUseCase(workoutProgramRepository = get()) }
    factory { UpdateWorkoutCompleteStatusUseCase(workoutProgramRepository = get()) }
    factory {
        UpdateWorkoutSetRepsDoneUseCase(
            workoutProgramRepository = get(),
            updateWorkoutCompleteStatus = get()
        )
    }

    factory { GetCurrentProgramIdUseCase(settingsDataStore = get()) }
    factory { GetWorkoutProgramSelectStatusUseCase(settingsDataStore = get()) }
    factory {
        SetCurrentWorkoutProgramIdUseCase(
            settingsDataStore = get(),
            setWorkoutProgramSelectStatus = get()
        )
    }
    factory { SetWorkoutProgramSelectStatusUseCase(settingsDataStore = get()) }
}