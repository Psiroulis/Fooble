package com.redpepper.fooble;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.redpepper.fooble.RecycleViewsAdapters.ExercicesRecViewAdater;

import java.util.ArrayList;
import java.util.List;

public class ExercicesList extends Activity {

    private RecyclerView exercisesList;

    private ExercicesRecViewAdater mAdapter;

    private Context context;

    private List<Exercise> allCategorysExercises;

    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercices_list);

        findTheViews();

        context = this;

        Intent intent = getIntent();

        categoryId = intent.getIntExtra("catid",0);

        allCategorysExercises = new ArrayList<>();

        new GetCategorysExercises().execute();

    }

    private void findTheViews(){
        exercisesList = findViewById(R.id.exec_recycleview);
    }

    private class GetCategorysExercises extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            mAdapter = new ExercicesRecViewAdater(allCategorysExercises,context);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            exercisesList.setLayoutManager(mLayoutManager);
            exercisesList.setItemAnimator(new DefaultItemAnimator());
            exercisesList.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
            exercisesList.setAdapter(mAdapter);
            exercisesList.setNestedScrollingEnabled(false);

            mAdapter.notifyDataSetChanged();
        }

        @Override
        protected String doInBackground(String... strings) {

            //get Exercises from api with catId

            Exercise exercise_one = new Exercise(1,"Ασκιση 1",1);
            Exercise exercise_two = new Exercise(2,"Ασκιση 2",2);
            Exercise exercise_three = new Exercise(3,"Ασκιση 3",3);

            allCategorysExercises.add(exercise_one);
            allCategorysExercises.add(exercise_two);
            allCategorysExercises.add(exercise_three);



            return null;
        }
    }
}

