package com.joesemper.pushupboard.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {
    suspend fun setCurrentWorkoutProgram(programId: Int)
    fun getCurrentWorkoutProgramId(): Flow<Int>
    suspend fun setWorkoutProgramSelectedStatus(isSelected: Boolean)
    fun isWorkoutProgramSelected(): Flow<Boolean>
}