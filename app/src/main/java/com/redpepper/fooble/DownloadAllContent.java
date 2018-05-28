package com.redpepper.fooble;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

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

            data.put("pass","");

            jparser.makeHttpUrlRequest(url,data);



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
