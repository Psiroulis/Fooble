package com.redpepper.fooble;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.redpepper.fooble.RecycleViewsAdapters.ExercicesRecViewAdater;
import com.redpepper.fooble.database.AppDatabase;
import com.redpepper.fooble.database.ExerciseDao;
import com.redpepper.fooble.database.ExerciseEntity;
import com.redpepper.fooble.myclasses.Exercise;
import com.redpepper.fooble.myclasses.HttpConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AllExercisesActivity extends Activity {

    private RecyclerView exercisesList;

    private ExercicesRecViewAdater mAdapter;

    private Context context;

    private List<Exercise> allCategorysExercises;

    private int categoryId;

    private int selectedAge;

    private List<Integer> allDoneExercises;

    private RelativeLayout loadingLayout;

    private HttpConnection jParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercices_list);

        findTheViews();

        context = this;

        Intent intent = getIntent();

        categoryId = intent.getIntExtra("catid",0);

        SharedPreferences prefs = context.getSharedPreferences("prefs",Context.MODE_PRIVATE);

        selectedAge = prefs.getInt("age",0);

        allCategorysExercises = new ArrayList<>();

        allDoneExercises = new ArrayList<>();

        jParser = new HttpConnection();

        //new GetCategorysExercises().execute();
        new GetExercisesFromDatabase().execute();
    }

    private void findTheViews(){
        exercisesList = findViewById(R.id.exec_recycleview);
        loadingLayout = findViewById(R.id.exercListLoadingLay);
    }

    private class GetCategorysExercises extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            loadingLayout.setVisibility(View.VISIBLE);
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

            loadingLayout.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... strings) {

            URL url = null;

            try{

                url = new URL("http://165.227.168.146/api/get-exercises-list");

            }catch (MalformedURLException ex){
                ex.printStackTrace();
            }

            HashMap<String,String> data = new HashMap<>();
            data.put("lang",String.valueOf(Locale.getDefault().getLanguage()));
            data.put("age",String.valueOf(selectedAge));
            data.put("category_id",String.valueOf(categoryId));

            try{

                JSONObject jObj = jParser.makeHttpUrlRequest(url,data,"Post");

                JSONArray exercisesArray = jObj.getJSONArray("exercises");

                for (int i = 0; i < exercisesArray.length(); i++){

                    JSONObject oneExercise = exercisesArray.getJSONObject(i);



                    Exercise exercise = new Exercise(
                            oneExercise.getInt("id"),
                            oneExercise.getString("name"),
                            oneExercise.getString("level"),
                            IsExerciseDone(allDoneExercises, oneExercise.getInt("id"))

                    );

                    allCategorysExercises.add(exercise);
                }

            }catch (JSONException ex){
                ex.printStackTrace();
            }

            return null;
        }
    }

    private class GetExercisesFromDatabase extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {

            AppDatabase db = AppDatabase.getInstance(context);

            ExerciseDao execDao = db.exerDao();

            List<ExerciseEntity> exercisesFromDb = execDao.getAll();

            if(exercisesFromDb.size() > 0){

                for(int i = 0; i < exercisesFromDb.size(); i++){

                    allDoneExercises.add(exercisesFromDb.get(i).getExerId());

                }

            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            new GetCategorysExercises().execute();
        }
    }

    private boolean IsExerciseDone(List list, int id){
        return  list.contains(id);
    }
}

