package com.redpepper.fooble;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.redpepper.fooble.RecycleViewsAdapters.CategoryRecViewAdapter;
import com.redpepper.fooble.RecycleViewsAdapters.ExercicesRecViewAdater;
import com.redpepper.fooble.database.AppDatabase;
import com.redpepper.fooble.database.CategoriesDao;
import com.redpepper.fooble.database.ExercisesEntity;
import com.redpepper.fooble.database.ExerscisesDao;

import java.util.List;

public class ExercicesList extends Activity {

    private RecyclerView exercisesList;
    private Context context;
    private int selectedCategoryId;
    private int selectedAge;
    private List<ExercisesEntity> allCategorysExercises;
    private ExercicesRecViewAdater mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices_list);

        Intent intent = getIntent();

        selectedCategoryId = intent.getIntExtra("categoryId",0);

        findTheViews();

        context = this;

    }

    private void findTheViews(){
        exercisesList = findViewById(R.id.exerc_recycleview);
    }

    private class GetCategorysExercises extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //exercisesList.setHasFixedSize(true);

            mAdapter = new ExercicesRecViewAdater(context,allCategorysExercises);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            exercisesList.setLayoutManager(mLayoutManager);
            exercisesList.setItemAnimator(new DefaultItemAnimator());
            exercisesList.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
            exercisesList.setAdapter(mAdapter);

            mAdapter.notifyDataSetChanged();
        }

        @Override
        protected String doInBackground(String... strings) {

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"AppDatabase").build();

            ExerscisesDao excdao = db.excDao();

            allCategorysExercises = excdao.getExercicesOfCategoryAndAge(selectedCategoryId,selectedAge);

            db.close();

            return null;
        }
    }
}

