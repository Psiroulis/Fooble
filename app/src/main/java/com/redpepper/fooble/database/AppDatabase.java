package com.redpepper.fooble.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ExerciseEntity.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    public abstract ExerciseDao exerDao();

    private static final String DB_NAME = "exercises.db";

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){

        if( instance == null ){

            instance = create(context);

        }

        return instance;
    }

    private static AppDatabase create(final Context context){

        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();

    }
}





