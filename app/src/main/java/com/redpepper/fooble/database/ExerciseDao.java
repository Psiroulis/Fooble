package com.redpepper.fooble.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM ExerciseEntity")
    List<ExerciseEntity> getAll();

    @Query("DELETE FROM ExerciseEntity WHERE exer_id = :id")
    void deleteById(int id);

    @Insert
    void insertAll(ExerciseEntity... exercises);

    @Delete
    void delete(ExerciseEntity exercise);


}
