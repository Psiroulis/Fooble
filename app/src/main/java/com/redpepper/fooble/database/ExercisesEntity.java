package com.redpepper.fooble.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ExercisesEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "age_min")
    private int age_min;

    @ColumnInfo(name = "age_max")
    private int age_max;

    @ColumnInfo(name = "level")
    private String level;

    @ColumnInfo(name = "category_id")
    private int category_id;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "bibliography")
    private String bibliography;
}
