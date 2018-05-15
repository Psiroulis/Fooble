package com.redpepper.fooble.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {CategoriesEntity.class,ExercisesEntity.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CategoriesDao catDao();

    public abstract ExerscisesDao excDao();


}