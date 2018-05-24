package com.redpepper.fooble.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CategoriesEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image_link")
    private String image_link;

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
