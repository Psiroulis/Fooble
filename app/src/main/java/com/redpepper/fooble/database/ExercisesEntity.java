package com.redpepper.fooble.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ExercisesEntity {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name="id")
    private int id;

    public ExercisesEntity(int id) {
        this.id = id;

    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() { return uid; }

    public int getId() {
        return id;
    }

}
