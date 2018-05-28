package com.redpepper.fooble;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.redpepper.fooble.database.AppDatabase;
import com.redpepper.fooble.database.CategoriesDao;
import com.redpepper.fooble.database.CategoriesEntity;
import com.redpepper.fooble.database.ExerscisesDao;

import org.json.JSONArray;
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

        Intent intent = new Intent(DownloadAllContent.this,MainActivity.class);

        startActivity(intent);

        DownloadAllContent.this.finish();

        //new GetDataFromServer().execute();
    }

    private  class GetDataFromServer extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {

            URL url = null;

            try {

                url = new URL("");

            }catch (MalformedURLException ex){

                ex.printStackTrace();

            }

            HashMap<String,String> data = new HashMap<>();

            data.put("pass","https://");

            JSONObject jobj =  jparser.makeHttpUrlRequest(url,data);

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"AppDatabase").build();

            CategoriesDao catdao = db.catDao();

            ExerscisesDao exdao = db.excDao();

            catdao.dropTableCategories();

            exdao.dropTableExercises();

            CategoriesEntity catentity = new CategoriesEntity("name","imagelink");

            catdao.insertCategories(catentity);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Intent intent = new Intent(DownloadAllContent.this,MainActivity.class);

            startActivity(intent);

            DownloadAllContent.this.finish();
        }


    }
}
