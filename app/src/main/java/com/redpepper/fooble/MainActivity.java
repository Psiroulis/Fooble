package com.redpepper.fooble;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        mVideoView = findViewById(R.id.videoView);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test);

        mVideoView.setVideoURI(uri);

        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                mediaPlayer.setLooping(true);

            }
        });

        Spinner agebox = findViewById(R.id.ageSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.age_group, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        agebox.setAdapter(adapter);

        agebox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position).toString();

                Toast.makeText(context,parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
