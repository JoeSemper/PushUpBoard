package com.joesemper.pushupboard.data.datasourse.converters

import com.joesemper.pushupboard.R
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseMuscleGroup
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseProgram
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseWorkout
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseWorkoutExercise
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseWorkoutExerciseWithMuscleGroup
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseWorkoutSet
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseWorkoutSetWithExercise
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseWorkoutWithWorkoutSets
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedMuscleGroup
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedProgram
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedWorkout
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedWorkoutExercise
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.entity.PrepopulatedWorkoutSet
import com.joesemper.pushupboard.domain.entity.MuscleGroup
import com.joesemper.pushupboard.domain.entity.Program
import com.joesemper.pushupboard.domain.entity.Workout
import com.joesemper.pushupboard.domain.entity.WorkoutExercise
import com.joesemper.pushupboard.domain.entity.WorkoutSet
import com.joesemper.pushupboard.domain.entity.WorkoutWithMuscleGroups

fun prepopulatedWorkoutToDatabaseWorkout(prepopulatedWorkout: PrepopulatedWorkout) =
    with(prepopulatedWorkout) {
        DatabaseWorkout(
            workoutId = workoutId ?: 0,
            date = 0,
            dayInProgram = dayInProgram ?: 0,
            programId = programId ?: 0
        )
    }

fun prepopulatedExerciseToDatabaseExercise(prepopulatedExercise: PrepopulatedWorkoutExercise) =
    with(prepopulatedExercise) {
        DatabaseWorkoutExercise(
            exerciseId = exerciseId ?: 0,
            exerciseName = exerciseName ?: "",
            muscleGroupId = muscleGroupId ?: 0,
            description = description ?: ""
        )
    }

fun prepopulatedMuscleGroupToDatabaseMuscleGroup(prepopulatedMuscleGroup: PrepopulatedMuscleGroup) =
    with(prepopulatedMuscleGroup) {
        DatabaseMuscleGroup(
            muscleGroupId = muscleGroupId ?: 0,
            muscleGroupName = muscleGroupName ?: "",
            muscleGroupResId = when(muscleGroupName) {
                "Chest" -> R.drawable.is_chest
                "Back" -> R.drawable.ic_back
                "Shoulders" -> R.drawable.ic_shoulders
                "Triceps" -> R.drawable.ic_triceps
                else -> R.drawable.ic_rest
            }
        )
    }

fun prepopulatedWorkoutSetToDatabaseWorkoutSet(prepopulatedWorkoutSet: PrepopulatedWorkoutSet) =
    with(prepopulatedWorkoutSet) {
        DatabaseWorkoutSet(
            workoutSetId = workoutSetId ?: 0,
            workoutId = workoutId ?: 0,
            exerciseId = exerciseId ?: 0,
            exerciseReps = exerciseReps ?: 0
        )
    }

fun prepopulatedProgramToDatabaseProgram(prepopulatedProgram: PrepopulatedProgram) =
    with(prepopulatedProgram) {
        DatabaseProgram(
            programId = programId ?: 0,
            programName = programName ?: ""
        )
    }

fun DatabaseProgram.toProgram() = Program(
    programId = programId,
    programName = programName
)

fun DatabaseWorkout.toWorkout() = Workout(
    workoutId = workoutId,
    programId = programId,
    date = millisecondsToDateString(date),
    dayInProgram = dayInProgram,
)

fun DatabaseWorkoutExercise.toWorkoutExercise() = WorkoutExercise(
    exerciseId = exerciseId,
    exerciseName = exerciseName,
    muscleGroupId = muscleGroupId,
    description = description
)

fun DatabaseWorkoutExerciseWithMuscleGroup.toWorkoutExercise() = WorkoutExercise(
    exerciseId = databaseWorkoutExercise.exerciseId,
    exerciseName = databaseWorkoutExercise.exerciseName,
    muscleGroupId = databaseWorkoutExercise.muscleGroupId,
    muscleGroup = databaseMuscleGroup.toMuscleGroup(),
    description = databaseWorkoutExercise.description

)

fun DatabaseWorkoutSetWithExercise.toWorkoutSet() = WorkoutSet(
    workoutSetId = databaseWorkoutSet.workoutSetId,
    workoutId = databaseWorkoutSet.workoutId,
    exercise = databaseWorkoutExercise.toWorkoutExercise(),
    exerciseReps = databaseWorkoutSet.exerciseReps,
    exerciseRepsDone = databaseWorkoutSet.exerciseRepsDone
)

fun DatabaseMuscleGroup.toMuscleGroup() = MuscleGroup(
    muscleGroupId = muscleGroupId,
    muscleGroupName = muscleGroupName,
    muscleGroupResId = muscleGroupResId
)

fun DatabaseWorkoutWithWorkoutSets.toWorkout() = Workout(
    workoutId = databaseWorkout.workoutId,
    programId = databaseWorkout.programId,
    date = millisecondsToDateString(databaseWorkout.date),
    dayInProgram = databaseWorkout.dayInProgram,
    isComplete = databaseWorkout.isComplete,
    workoutSets = databaseWorkoutSets.map { it.toWorkoutSet() }
)

fun workoutsWithMuscleGroupsMapToEntity(map: Map<DatabaseWorkout, Set<DatabaseMuscleGroup>>): List<WorkoutWithMuscleGroups> {
    val list = map.toList().sortedBy { it.first.dayInProgram }
    return list.map { pair ->
        WorkoutWithMuscleGroups(
            workoutId = pair.first.workoutId,
            programId = pair.first.programId,
            date = millisecondsToDateString(pair.first.date),
            dayInProgram = pair.first.dayInProgram,
            isComplete = pair.first.isComplete,
            muscleGroups = pair.second.map { it.toMuscleGroup() }.toSet()
        )
    }
}