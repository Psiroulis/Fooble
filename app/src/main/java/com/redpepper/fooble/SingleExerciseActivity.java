package com.redpepper.fooble;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.redpepper.fooble.database.AppDatabase;
import com.redpepper.fooble.database.ExerciseDao;
import com.redpepper.fooble.database.ExerciseEntity;

import java.util.ArrayList;
import java.util.List;

public class SingleExerciseActivity extends YouTubeBaseActivity {

    YouTubePlayerView yView;

    YouTubePlayer.OnInitializedListener yListener;

    private int exerciseId;

    private ToggleButton doneButton;

    private Context context;

    private List<Integer> allDoneExercises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise);

        findTheViews();

        context = this;

        allDoneExercises = new ArrayList<>();

        Intent intent = getIntent();

        exerciseId = intent.getIntExtra("exerciseId",0);

        yListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo("_AhLf9ZKvmE");
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE|YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
                youTubePlayer.setShowFullscreenButton(true);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        yView.initialize("AIzaSyDJNAddtgTpogXIHUaW1gagk4btE76oomc",yListener);

        new GetExercisesFromDatabase().execute();
    }

    private void findTheViews(){
        yView = findViewById(R.id.view);
        doneButton = findViewById(R.id.singleExercDoneButton);
    }

    private class GetExercisesFromDatabase extends AsyncTask<String, String, String>{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(IsExerciseDone(allDoneExercises,exerciseId)){

                doneButton.setChecked(true);

            }else{
                doneButton.setChecked(false);
            }

            doneButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        //insert id

                        new InsertIdToDb().execute();



                    }else{
                        //delete id

                        new DeleteIdFromDb().execute();



                    }
                }
            });
        }

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

    }

    private boolean IsExerciseDone(List list, int id){
        return  list.contains(id);
    }

    private class InsertIdToDb extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {

            AppDatabase db = AppDatabase.getInstance(context);

            ExerciseDao execDao = db.exerDao();

            execDao.insertAll(new ExerciseEntity(exerciseId));

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(context,"INSERT",Toast.LENGTH_SHORT).show();

        }
    }

    private class DeleteIdFromDb extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {

            AppDatabase db = AppDatabase.getInstance(context);

            ExerciseDao execDao = db.exerDao();

            execDao.deleteById(exerciseId);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(context,"DELETE",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}


