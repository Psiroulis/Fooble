package com.redpepper.fooble;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

        final Spinner agebox = findViewById(R.id.ageSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.age_group, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        agebox.setAdapter(adapter);

        agebox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(context,parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();

                String ageGroup = parent.getItemAtPosition(position).toString();

                Intent intent = new Intent(MainActivity.this,AgeInformation.class);

                intent.putExtra("agegroup",ageGroup);

                startActivity(intent);

                MainActivity.this.finish();


                switch (ageGroup){
                    case "8-10":
                        break;
                    case "11-12":
                        break;
                    case "13-15":
                        break;
                    case "16-17":
                        break;
                    case "18+":
                        break;
                        default:
                            Toast.makeText(context,"No AgeGroup Selected",Toast.LENGTH_SHORT).show();
                            break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
