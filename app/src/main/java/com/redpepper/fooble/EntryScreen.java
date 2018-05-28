package com.redpepper.fooble;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class EntryScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entry_screen);

        final Context context = this;

        SharedPreferences prefs = context.getSharedPreferences("prefs",Context.MODE_PRIVATE);


        //int firstTime = prefs.getInt("firtstime",0);

        int firstTime = 1;

        if(firstTime != 0){

            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    CheckConnectionAndProceed(context);

                }
            },5000);

        }else {

            //todo:dialog to inform that connection is required for first time;
            Toast.makeText(context,"To run App for first time you need internet connection for extra content to be downloaded",Toast.LENGTH_LONG).show();

        }





    }

    private void CheckConnectionAndProceed(Context context){
        Connectivity connection = new Connectivity();

        if(connection.isConnected(context)){

            Intent intent = new Intent(EntryScreen.this,DownloadAllContent.class);

            startActivity(intent);

            EntryScreen.this.finish();

        }else{

            //todo: Dialog to procced with no internet Connection
            Intent intent = new Intent(EntryScreen.this,MainActivity.class);

            startActivity(intent);

            EntryScreen.this.finish();
            Toast.makeText(context,"There is no internet Connection",Toast.LENGTH_SHORT).show();
        }
    }
}
