package com.joesemper.pushupboard.data.repository

import com.joesemper.pushupboard.data.datasourse.converters.*
import com.joesemper.pushupboard.data.datasourse.room.main.dao.WorkoutProgramDao
import com.joesemper.pushupboard.data.datasourse.room.main.entity.DatabaseWorkoutWithWorkoutSets
import com.joesemper.pushupboard.data.datasourse.room.prepopulated.dao.PrepopulatedProgramDao
import com.joesemper.pushupboard.domain.entity.*
import com.joesemper.pushupboard.domain.repository.WorkoutProgramRepository
import kotlinx.coroutines.flow.*
import org.koin.java.KoinJavaComponent.inject


class WorkoutProgramRepositoryImpl(
    private val workoutProgramDao: WorkoutProgramDao
) : WorkoutProgramRepository {

    override suspend fun isEmpty() = workoutProgramDao.isEmpty()

    override suspend fun initialise() {
        val prepopulatedProgramDao: PrepopulatedProgramDao by inject(PrepopulatedProgramDao::class.java)

        val muscleGroups = prepopulatedProgramDao.getAllMuscleGroups()
        val exercises = prepopulatedProgramDao.getAllWorkoutExercises()
        val workoutSets = prepopulatedProgramDao.getAllWorkoutSets()
        val workouts = prepopulatedProgramDao.getAllWorkouts()
        val programs = prepopulatedProgramDao.getAllPrograms()

        workoutProgramDao.apply {
            insertMuscleGroups(muscleGroups.map { prepopulatedMuscleGroupToDatabaseMuscleGroup(it) })
            insertPrograms(programs.map { prepopulatedProgramToDatabaseProgram(it) })
            insertWorkouts(workouts.map { prepopulatedWorkoutToDatabaseWorkout(it) })
            insertExercises(exercises.map { prepopulatedExerciseToDatabaseExercise(it) })
            insertWorkoutSets(workoutSets.map { prepopulatedWorkoutSetToDatabaseWorkoutSet(it) })
        }
    }

    override fun getAllPrograms(): Flow<List<Program>> =
        workoutProgramDao.getAllPrograms().map { databasePrograms ->
            databasePrograms.map { it.toProgram() }
        }

    override fun getWorkoutWithSetsById(workoutId: Int) =
        workoutProgramDao.getWorkoutWithSetsById(workoutId).map { databaseWorkoutWithSets ->
            databaseWorkoutWithSets.toWorkout()
        }

    override fun getWorkoutSetsForWorkout(workoutId: Int) =
        workoutProgramDao.getWorkoutSetsWithExercises(workoutId)
            .map { databaseWorkoutSetWithExercise ->
                databaseWorkoutSetWithExercise.map { it.toWorkoutSet() }
            }

    override fun getWorkoutsForProgram(programId: Int) =
        workoutProgramDao.getWorkoutsForProgramWithMuscleGroups(programId).map { map ->
            workoutsWithMuscleGroupsMapToEntity(map)
        }

    override fun getProgramById(programId: Int) =
        workoutProgramDao.getProgramById(programId).map { databaseProgram ->
            databaseProgram.toProgram()
        }

    override suspend fun getWorkoutSetById(workoutSetId: Int) =
        workoutProgramDao.getWorkoutSetWithExercisesById(workoutSetId).toWorkoutSet()

    override suspend fun updateWorkoutCompleteStatus(workoutId: Int, isComplete: Boolean) {
        workoutProgramDao.updateWorkoutCompleteStatus(
            workoutId = workoutId,
            isComplete = isComplete
        )
    }

    override suspend fun updateWorkoutSetRepsDone(workoutSetId: Int, repsDone: Int) {
        workoutProgramDao.updateWorkoutSetRepsDone(workoutSetId = workoutSetId, repsDone = repsDone)
    }

}