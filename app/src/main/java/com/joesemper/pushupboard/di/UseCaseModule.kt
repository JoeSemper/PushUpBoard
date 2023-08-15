package com.joesemper.pushupboard.di

import com.joesemper.pushupboard.domain.use_case.GetAllWorkoutProgramsUseCase
import com.joesemper.pushupboard.domain.use_case.GetCurrentProgramIdUseCase
import com.joesemper.pushupboard.domain.use_case.GetWorkoutByIdUseCase
import com.joesemper.pushupboard.domain.use_case.GetWorkoutProgramByIdUseCase
import com.joesemper.pushupboard.domain.use_case.GetWorkoutProgramSelectStatusUseCase
import com.joesemper.pushupboard.domain.use_case.GetWorkoutSetsForWorkoutUseCase
import com.joesemper.pushupboard.domain.use_case.GetWorkoutsForProgramUseCase
import com.joesemper.pushupboard.domain.use_case.InitiateDatabaseUseCase
import com.joesemper.pushupboard.domain.use_case.SetCurrentWorkoutProgramIdUseCase
import com.joesemper.pushupboard.domain.use_case.SetWorkoutProgramSelectStatusUseCase
import com.joesemper.pushupboard.domain.use_case.UpdateWorkoutCompleteStatusUseCase
import com.joesemper.pushupboard.domain.use_case.UpdateWorkoutDatesForProgramUseCase
import com.joesemper.pushupboard.domain.use_case.UpdateWorkoutSetRepsDoneUseCase
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
    factory { UpdateWorkoutDatesForProgramUseCase(workoutProgramRepository = get()) }
}