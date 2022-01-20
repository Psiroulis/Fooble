package com.redpepper.fooble.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExerciseEntity {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "exer_id")
    private int exerId;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getExerId() {
        return exerId;
    }

    public void setExerId(int exerId) {
        this.exerId = exerId;
    }

    public ExerciseEntity(int exerId) {
        this.exerId = exerId;
    }
}
