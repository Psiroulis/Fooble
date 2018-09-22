package com.redpepper.fooble.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExerscisesDao {

    @Query("SELECT * FROM ExercisesEntity")
    List<ExercisesEntity> getAllExercises();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExercices(ExercisesEntity... exercisesEntity);

    @Query("DELETE FROM ExercisesEntity")
    void dropTableExercises();
}
