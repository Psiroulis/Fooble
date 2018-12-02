package com.redpepper.fooble;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

public class MainActivity extends FragmentActivity {

    private VideoView mVideoView;

    private Button selectAgeButton;

    private static Context context;

    private AdView mAdView;

    private RelativeLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = findViewById(R.id.videoView);
        selectAgeButton = findViewById(R.id.selectagebutton);
        loadingLayout = findViewById(R.id.mainLoadingLayout);


        context = this;

        mAdView = findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice("7BCF8D80D2D2720C44AED185E72590D3").build();
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

                loadingLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);

                loadingLayout.setVisibility(View.GONE);
                Log.e("blepo","TO problem" + i);
            }

        });

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
                newFragment.show(getSupportFragmentManager(),"datePicker");

                //displayFragment();

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

//    public void displayFragment() {
//
//        BirthdayPickerFragment simpleFragment = BirthdayPickerFragment.newInstance();
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragmentTransaction.add(R.id.fragment_container,simpleFragment).addToBackStack(null).commit();
//
//    }
}


