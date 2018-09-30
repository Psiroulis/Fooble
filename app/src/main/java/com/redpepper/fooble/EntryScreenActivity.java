package com.redpepper.fooble;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.redpepper.fooble.myclasses.Connectivity;
import com.redpepper.fooble.myclasses.LoadingAnimation;

public class EntryScreenActivity extends Activity {

    private ImageView loaderImageView;

    private LoadingAnimation loadbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entry_screen);

        loaderImageView = findViewById(R.id.loaderImgv);

        final Context context = this;

        loadbar = new LoadingAnimation(loaderImageView,1000,context);

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

            Intent intent = new Intent(EntryScreenActivity.this,MainActivity.class);

            startActivity(intent);

            loadbar.stopTheLoader();

            EntryScreenActivity.this.finish();

        }else{

            //todo: Dialog to inform for connection need
            Toast.makeText(context,"There is no internet Connection",Toast.LENGTH_SHORT).show();
        }
    }
}
