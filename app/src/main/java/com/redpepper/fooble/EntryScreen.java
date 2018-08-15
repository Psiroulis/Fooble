package com.redpepper.fooble;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

public class EntryScreen extends Activity {

    private ImageView loaderImageView;

    private LoadingBar loadbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entry_screen);

        loaderImageView = findViewById(R.id.loaderImgv);

        final Context context = this;

        loadbar = new LoadingBar(loaderImageView,1000,context);

        loadbar.playTheLoader();

        Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    CheckConnectionAndProceed(context);

                }
            },7000);



    }

    private void CheckConnectionAndProceed(Context context){
        Connectivity connection = new Connectivity();

        if(connection.isConnected(context)){

            Intent intent = new Intent(EntryScreen.this,MainActivity.class);

            startActivity(intent);

            loadbar.stopTheLoader();

            EntryScreen.this.finish();

        }else{

            //todo: Dialog to inform for connection need
            Toast.makeText(context,"There is no internet Connection",Toast.LENGTH_SHORT).show();
        }
    }
}
