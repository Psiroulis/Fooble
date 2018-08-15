package com.redpepper.fooble;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.redpepper.fooble.database.AppDatabase;
import com.redpepper.fooble.database.CategoriesDao;
import com.redpepper.fooble.database.CategoriesEntity;
import com.redpepper.fooble.database.ExercisesEntity;
import com.redpepper.fooble.database.ExerscisesDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DownloadAllContent extends Activity {

    private static HttpConnection jparser = new HttpConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_all_content);

        new GetDataFromServer().execute();
    }

    private  class GetDataFromServer extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //todo:Progress Dialog

            Log.d("blepo","Start Downloaling");


        }

        @Override
        protected String doInBackground(String... strings) {

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"AppDatabase").build();

            CategoriesDao catdao = db.catDao();

            ExerscisesDao exdao = db.excDao();

            catdao.dropTableCategories();

            exdao.dropTableExercises();

            URL url = null;

            try {

                url = new URL("http://92.222.242.161/api/get-all-info");

            }catch (MalformedURLException ex){

                ex.printStackTrace();

            }

            HashMap<String,String> data = new HashMap<>();

            data.put("pass","");

            JSONObject jobj =  jparser.makeHttpUrlRequest(url,data,"Get");

            try{

                JSONArray categoriesArray = jobj.getJSONArray("categories");
                JSONArray exercisesArray = jobj.getJSONArray("exercises");

               for(int i = 0; i< categoriesArray.length(); i++){

                    JSONObject onecategory = categoriesArray.getJSONObject(i);

                    int id = onecategory.getInt("id");
                    String name = onecategory.getString("name");
                    String imagelink = onecategory.getString("image_link");

                    //Log.d("blepo","Categories ID= "+id+ " Name = "+ name);


                   CategoriesEntity catentity = new CategoriesEntity(id,name,imagelink);

                   catdao.insertCategories(catentity);


                }

                for(int i = 0; i< exercisesArray.length(); i++){

                    JSONObject oneexercise = exercisesArray.getJSONObject(i);

                    int id = oneexercise.getInt("id");
                    String name = oneexercise.getString("name");
                    int agemin = oneexercise.getInt("age_min");
                    int agemax = oneexercise.getInt("age_max");
                    String level = oneexercise.getString("level");
                    int category_id = oneexercise.getInt("category_id");
                    String description = oneexercise.getString("description");
                    String bibliography = oneexercise.getString("bibliography");

//                  Log.d("blepo","Exercise id = "+id+"name="+name+"agemin="+agemin+"agemax="+agemax+"level="+level);

//                  ExercisesEntity exentity = new ExercisesEntity(id,name,agemin,agemax,level,category_id,description,bibliography);
//                  exdao.insertExercices(exentity);

               }

            }catch (JSONException ex){
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("blepo","Stops Downloaling");

            Intent intent = new Intent(DownloadAllContent.this,MainActivity.class);

            startActivity(intent);

            DownloadAllContent.this.finish();
        }


    }
}
