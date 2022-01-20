package com.redpepper.fooble;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.redpepper.fooble.myfragments.BirthdayPickerFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private VideoView mVideoView;

    private Button selectAgeButton;

    private static Context context;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = findViewById(R.id.videoView);
        selectAgeButton = findViewById(R.id.selectagebutton);

        context = this;

        mAdView = findViewById(R.id.adView);

        List<String> testDeviceIds = Arrays.asList("7BCF8D80D2D2720C44AED185E72590D3");

        RequestConfiguration requestConfiguration
                = new RequestConfiguration.Builder()
                .setTestDeviceIds(testDeviceIds)
                .build();
        MobileAds.setRequestConfiguration(requestConfiguration);

        mAdView.loadAd(new AdRequest.Builder().build());

        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();


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

//                DialogFragment newFragment = new DatePickerFragment();
//                newFragment.show(getFragmentManager(),"datePicker");

                Intent intent = new Intent(context,AgeInfoActivity.class);

                startActivity(intent);

            }
        });

    }

    public void displayFragment() {

        BirthdayPickerFragment simpleFragment = BirthdayPickerFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container,simpleFragment).addToBackStack(null).commit();

    }
}


