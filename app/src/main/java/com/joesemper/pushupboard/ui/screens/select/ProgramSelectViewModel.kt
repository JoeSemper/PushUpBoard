package com.joesemper.pushupboard.ui.screens.select

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joesemper.pushupboard.domain.entity.Program
import com.joesemper.pushupboard.domain.use_case.GetAllWorkoutProgramsUseCase
import com.joesemper.pushupboard.domain.use_case.SetCurrentWorkoutProgramIdUseCase
import kotlinx.coroutines.launch

class ProgramSelectViewModel(
    private val getAllWorkoutPrograms: GetAllWorkoutProgramsUseCase,
    private val setCurrentProgramId: SetCurrentWorkoutProgramIdUseCase
): ViewModel() {

    var uiState by mutableStateOf(SelectProgramScreenState())
        private set

    init {
        loadData()
    }

    private fun loadData() {
        uiState = uiState.copy(isLoading = true)

        viewModelScope.launch {
            getAllWorkoutPrograms().collect { programs ->
                uiState = uiState.copy(
                    isLoading = false,
                    programs = programs
                )
            }
        }
    }

    fun onSelectProgram(programId: Int) {
        viewModelScope.launch {
            setCurrentProgramId(programId)
            uiState = uiState.copy(isSelected = true)
        }
    }
}

data class SelectProgramScreenState(
    val isLoading: Boolean = true,
    val programs: List<Program> = listOf(),
    val isSelected: Boolean = false
)