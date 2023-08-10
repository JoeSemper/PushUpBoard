package com.joesemper.pushupboard.di

import com.joesemper.pushupboard.ui.MainViewModel
import com.joesemper.pushupboard.ui.screens.home.HomeViewModel
import com.joesemper.pushupboard.ui.screens.select.ProgramSelectViewModel
import com.joesemper.pushupboard.ui.screens.workout.WorkoutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { WorkoutViewModel(get(), get(), get()) }
    viewModel { ProgramSelectViewModel(get(), get()) }
}