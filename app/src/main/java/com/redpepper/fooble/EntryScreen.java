package com.redpepper.fooble;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class EntryScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entry_screen);

        final Context context = this;

        final Connectivity connection = new Connectivity();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(connection.isConnected(context)){

                    Intent intent = new Intent(EntryScreen.this,MainActivity.class);

                    startActivity(intent);

                    EntryScreen.this.finish();

                }else{

                    Toast.makeText(context,"There is no internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        },5000);



    }
}
