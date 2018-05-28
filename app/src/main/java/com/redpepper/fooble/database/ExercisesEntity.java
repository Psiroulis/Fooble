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

    public ExercisesEntity(String name, int age_min, int age_max, String level, int category_id, String description, String bibliography) {
        this.name = name;
        this.age_min = age_min;
        this.age_max = age_max;
        this.level = level;
        this.category_id = category_id;
        this.description = description;
        this.bibliography = bibliography;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge_min() {
        return age_min;
    }

    public int getAge_max() {
        return age_max;
    }

    public String getLevel() {
        return level;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getDescription() {
        return description;
    }

    public String getBibliography() {
        return bibliography;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }

    public void setAge_max(int age_max) {
        this.age_max = age_max;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }
}
