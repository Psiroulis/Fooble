package com.redpepper.fooble.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
