package com.joesemper.pushupboard.ui.screens.workout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joesemper.pushupboard.domain.entity.Workout
import com.joesemper.pushupboard.domain.use_case.GetWorkoutByIdUseCase
import com.joesemper.pushupboard.domain.use_case.UpdateWorkoutSetRepsDoneUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WorkoutViewModel(
    savedStateHandle: SavedStateHandle,
    private val getWorkoutById: GetWorkoutByIdUseCase,
    private val updateWorkoutSetRepsDone: UpdateWorkoutSetRepsDoneUseCase
) : ViewModel() {
    val workoutId = MutableStateFlow<Int>(checkNotNull(savedStateHandle["workoutId"]))

    var workoutUiState by mutableStateOf(WorkoutScreenState())
        private set

    init {
        subscribeOnData()
    }

    private fun subscribeOnData() {
        workoutUiState = workoutUiState.copy(isLoading = true)

        viewModelScope.launch {
            getWorkoutById(workoutId.value).collect { workout ->
                workoutUiState = workoutUiState.copy(
                    isLoading = false,
                    workout = workout
                )
            }
        }
    }

    fun updateRepsDone(setId: Int, repsDoneDelta: Int) {
        val workoutSet = workoutUiState.workout.workoutSets.find { it.workoutSetId == setId }

        viewModelScope.launch {
            workoutSet?.let {
                val repsDone = it.exerciseRepsDone + repsDoneDelta

                updateWorkoutSetRepsDone(
                    workoutId = workoutUiState.workout.workoutId,
                    workoutSetId = setId,
                    repsDone = repsDone,
                )
            }

        }
    }

}

data class WorkoutScreenState(
    val isLoading: Boolean = true,
    val workout: Workout = Workout()
)