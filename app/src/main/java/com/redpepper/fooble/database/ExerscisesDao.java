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

    @Query("SELECT * FROM ExercisesEntity WHERE category_id = :categoryId AND age_min >= :age AND :age <= age_max")
    List<ExercisesEntity> getExercicesOfCategoryAndAge(int categoryId,int age);
}
