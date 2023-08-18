package com.joesemper.pushupboard.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joesemper.pushupboard.domain.entity.WorkoutWithMuscleGroups
import com.joesemper.pushupboard.domain.use_case.GetCurrentProgramIdUseCase
import com.joesemper.pushupboard.domain.use_case.GetWorkoutProgramByIdUseCase
import com.joesemper.pushupboard.domain.use_case.GetWorkoutsForProgramUseCase
import com.joesemper.pushupboard.domain.use_case.UpdateWorkoutDatesForProgramUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getWorkoutsForProgram: GetWorkoutsForProgramUseCase,
    private val getWorkoutProgramById: GetWorkoutProgramByIdUseCase,
    private val getCurrentProgramId: GetCurrentProgramIdUseCase,
    private val updateWorkoutDatesForProgram: UpdateWorkoutDatesForProgramUseCase
) : ViewModel() {

    var homeState by mutableStateOf(HomeScreenState())
        private set

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {

            getCurrentProgramId().collect { id ->
                getWorkoutProgramById(programId = id).collectLatest { program ->

                    homeState = homeState.copy(
                        topBarState = homeState.topBarState.copy(title = program.programName)
                    )

                    getWorkoutsForProgram(programId = program.programId).collectLatest { workouts ->
                        homeState = homeState.copy(
                            isLoading = false,
                            workoutsState = WorkoutsState(
                                workouts = workouts.map {
                                    WorkoutItemState(
                                        workout = it,
                                        isNext = isWorkoutNext(it.workoutId, workouts)
                                    )
                                },
                                programState = getWorkoutProgramState(workouts)
                            ),
                            programProgress = calculateProgress(workouts),
                        )
                    }
                }
            }

        }
    }

    private fun calculateProgress(workouts: List<WorkoutWithMuscleGroups>) = ProgramProgress(
        totalWorkouts = workouts.size,
        workoutsDone = workouts.filter { it.isComplete }.size,
        percentProgress = if (workouts.isNotEmpty()) {
            ((workouts.filter { it.isComplete }.size.toFloat() / workouts.size) * 100).toInt()
        } else {
            0
        }
    )

    fun updateDates() {
        viewModelScope.launch {
            updateWorkoutDatesForProgram(getCurrentProgramId().first())
        }
    }

    private fun getWorkoutProgramState(workouts: List<WorkoutWithMuscleGroups>) =
        when {
            workouts.none { it.isComplete } -> WorkoutProgramStatus.NotStarted
            workouts.none { !it.isComplete } -> WorkoutProgramStatus.Complete
            else -> WorkoutProgramStatus.InProgress
        }

    private fun isWorkoutNext(
        workoutId: Int,
        workouts: List<WorkoutWithMuscleGroups>
    ): Boolean {
        return workouts.sortedBy { it.dayInProgram }.find { !it.isComplete }?.workoutId == workoutId
    }

}

data class HomeScreenState(
    val isLoading: Boolean = true,
    val workoutsState: WorkoutsState = WorkoutsState(),
    val topBarState: HomeTopBarState = HomeTopBarState(),
    val programProgress: ProgramProgress = ProgramProgress()
)

data class WorkoutsState(
    val workouts: List<WorkoutItemState> = listOf(),
    val programState: WorkoutProgramStatus = WorkoutProgramStatus.NotSpecified
)

data class WorkoutItemState(
    val workout: WorkoutWithMuscleGroups,
    val isNext: Boolean,
)

data class HomeTopBarState(
    val title: String = ""
)

data class ProgramProgress(
    val workoutsDone: Int = 0,
    val totalWorkouts: Int = 0,
    val percentProgress: Int = 0
)

sealed class WorkoutProgramStatus() {
    object InProgress : WorkoutProgramStatus()
    object Complete : WorkoutProgramStatus()
    object NotStarted : WorkoutProgramStatus()
    object NotSpecified : WorkoutProgramStatus()
}
