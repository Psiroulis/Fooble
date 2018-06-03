package com.redpepper.fooble;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.VideoView;

import com.redpepper.fooble.database.AppDatabase;
import com.redpepper.fooble.database.CategoriesDao;
import com.redpepper.fooble.database.CategoriesEntity;
import com.redpepper.fooble.database.ExercisesEntity;
import com.redpepper.fooble.database.ExerscisesDao;

import java.util.List;

public class MainActivity extends Activity {

    private VideoView mVideoView;

    boolean userSelect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = findViewById(R.id.videoView);

        final Spinner agebox = findViewById(R.id.ageSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.age_group, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        agebox.setAdapter(adapter);

        agebox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        userSelect = true;

                        break;
                    case MotionEvent.ACTION_UP:

                        view.performClick();

                        break;
                    default:
                        break;
                }

                return true;
            }


        });




        agebox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(userSelect){

                    //Toast.makeText(context,parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();

                    if(position != 0) {

                        String ageGroup = parent.getItemAtPosition(position).toString();

                        Intent intent = new Intent(MainActivity.this, AgeInformation.class);

                        intent.putExtra("agegroup", ageGroup);

                        startActivity(intent);

                        userSelect = false;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test);

        mVideoView.setVideoURI(uri);

        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                mediaPlayer.setLooping(true);

            }
        });

        new GetAllData().execute();
    }

    private class GetAllData extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"AppDatabase").build();

            CategoriesDao catdao = db.catDao();

            ExerscisesDao exdao = db.excDao();

            List<CategoriesEntity> allCategories = catdao.getAllCategories();
            List<ExercisesEntity> allExercises = exdao.getAllExercises();

            db.close();

            for (int i = 0; i< allCategories.size(); i++){

                CategoriesEntity entity = allCategories.get(i);
                Log.d("Blepo","Categorie-> id="+entity.getId()+" name="+entity.getName()+"imlink="+entity.getImage_link());
            }

            for (int i = 0; i< allExercises.size(); i++){

                ExercisesEntity entity = allExercises.get(i);
                Log.d("Blepo","Exercise-> id="+entity.getId()+" name="+entity.getName()+"agemin= "+entity.getAge_min());
            }




            return null;
        }
    }
}
