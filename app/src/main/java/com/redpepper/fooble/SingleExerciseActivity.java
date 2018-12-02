package com.redpepper.fooble;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.redpepper.fooble.database.AppDatabase;
import com.redpepper.fooble.database.ExerciseDao;
import com.redpepper.fooble.database.ExerciseEntity;
import com.redpepper.fooble.myclasses.HttpConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class SingleExerciseActivity extends YouTubeBaseActivity {

    YouTubePlayerView yView;

    YouTubePlayer.OnInitializedListener yListener;

    private int exerciseId;

    private ToggleButton doneButton;

    private Context context;

    private List<Integer> allDoneExercises;

    private HttpConnection jParser;

    private TextView exerciceTitle;

    private RelativeLayout loadingLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise);

        findTheViews();

        context = this;

        jParser = new HttpConnection();

        allDoneExercises = new ArrayList<>();

        Intent intent = getIntent();

        exerciseId = intent.getIntExtra("exerciseId",0);

        new GetExercisesInfoFromNetwork().execute();

    }

    private void findTheViews(){
        yView = findViewById(R.id.view);
        doneButton = findViewById(R.id.singleExercDoneButton);
        exerciceTitle = findViewById(R.id.exerciceTitleTxt);
        loadingLay = findViewById(R.id.singleExercLoginLay);
    }


    private class GetExercisesInfoFromNetwork extends AsyncTask<String, String, String>{

        String name;

        String videoCode;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            URL url = null;

            try{

                url = new URL("http://165.227.168.146/api/get-exercise");

            }catch (MalformedURLException ex){
                ex.printStackTrace();
            }

            HashMap<String,String> data = new HashMap<>();
            data.put("lang",String.valueOf(Locale.getDefault().getLanguage()));
            data.put("exercise_id",String.valueOf(exerciseId));

            try{

                JSONObject jObj = jParser.makeHttpUrlRequest(url,data,"Post");

                JSONObject exr = jObj.getJSONObject("exercise");

                name = exr.getString("name");

                videoCode = exr.getString("video");

            }catch (JSONException ex){
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            exerciceTitle.setText(name);

            yListener = new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.cueVideo(videoCode);
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

            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    loadingLay.setVisibility(View.GONE);

                }
            },4 * 1000);

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


