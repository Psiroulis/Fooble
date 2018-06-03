package com.redpepper.fooble.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CategoriesEntity {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image_link")
    private String image_link;

    public CategoriesEntity(int id,String name, String image_link) {
        this.id = id;
        this.name = name;
        this.image_link = image_link;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() { return uid; }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}
