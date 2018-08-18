package com.redpepper.fooble;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import com.redpepper.fooble.RecycleViewsAdapters.CategoryRecViewAdapter;
import com.redpepper.fooble.database.AppDatabase;
import com.redpepper.fooble.database.CategoriesDao;
import com.redpepper.fooble.database.CategoriesEntity;
import com.redpepper.fooble.database.ExercisesEntity;
import com.redpepper.fooble.database.ExerscisesDao;

import java.util.List;

public class AllCategoriesList extends Activity {

    private int selectedAge;

    private RecyclerView categoryList;

    private CategoryRecViewAdapter mAdapter;

    private Context context;

    private List<Category> allCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories_list);

        findTheViews();

        context = this;
    }


    @Override
    protected void onResume() {
        super.onResume();

        //selectedAge = getIntent().getIntExtra("selectedAge",0);

    }

    private void findTheViews(){

        categoryList = findViewById(R.id.cat_recycleview);

    }

    private class GetAllCategories extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            categoryList.setHasFixedSize(true);

            mAdapter = new CategoryRecViewAdapter(context,allCategories);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            categoryList.setLayoutManager(mLayoutManager);
            categoryList.setItemAnimator(new DefaultItemAnimator());
            categoryList.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
            categoryList.setAdapter(mAdapter);

            mAdapter.notifyDataSetChanged();

        }

        @Override
        protected String doInBackground(String... strings) {

            Category categoryOne = new Category(1,"ΚΑΤΗΓΟΡΙΑ ΕΝΑ","ΜΙΚΡΗ ΠΕΡΙΓΡΑΦΗ","");
            Category categoryTwo = new Category(2,"ΚΑΤΗΓΟΡΙΑ ΔΥΟ","ΜΙΚΡΗ ΠΕΡΙΓΡΑΦΗ 2","");

            allCategories.add(categoryOne);
            allCategories.add(categoryTwo);

            return null;
        }
    }
}
