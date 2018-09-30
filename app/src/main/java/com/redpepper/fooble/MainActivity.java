package com.redpepper.fooble;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.VideoView;

import java.util.Calendar;

public class MainActivity extends Activity {

    private VideoView mVideoView;

    private Button selectAgeButton;

    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = findViewById(R.id.videoView);
        selectAgeButton = findViewById(R.id.selectagebutton);

        context = this;


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

        selectAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"datePicker");

            }
        });

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            SharedPreferences prefs = context.getSharedPreferences("prefs",Context.MODE_PRIVATE);

            SharedPreferences.Editor pe= prefs.edit();

            pe.putInt("age",getAge(year,month,day));

            pe.commit();

            Intent intent = new Intent(context,AgeInfoActivity.class);

            startActivity(intent);

        }

        public int getAge(int DOByear, int DOBmonth, int DOBday) {

            int age;

            final Calendar calenderToday = Calendar.getInstance();
            int currentYear = calenderToday.get(Calendar.YEAR);
            int currentMonth = 1 + calenderToday.get(Calendar.MONTH);
            int todayDay = calenderToday.get(Calendar.DAY_OF_MONTH);

            age = currentYear - DOByear;

            if(DOBmonth > currentMonth) {
                --age;
            } else if(DOBmonth == currentMonth) {
                if(DOBday > todayDay){
                    --age;
                }
            }
            return age;
        }
    }
}


