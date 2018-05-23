package com.redpepper.fooble.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CategoriesDao {

    @Query("SELECT * FROM CategoriesEntity")
    List<CategoriesEntity> getAllCategories();

    @Query("SELECT * FROM CategoriesEntity WHERE id = :Id")
    CategoriesEntity GetOneCategoryById(int Id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(CategoriesEntity... categoriesEntities);

    @Query("DELETE FROM CategoriesEntity")
    void dropTableCategories();
}
