package com.redpepper.fooble;

import android.content.Intent;
import android.os.Bundle;


import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ExerciseActivity extends YouTubeBaseActivity {

    YouTubePlayerView yView;

    YouTubePlayer.OnInitializedListener yListener;

    private int exerciseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();

        exerciseId = intent.getIntExtra("exerciseId",0);




        yView = findViewById(R.id.view);

        yListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo("_AhLf9ZKvmE");
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE|YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
                youTubePlayer.setShowFullscreenButton(true);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        yView.initialize("AIzaSyDJNAddtgTpogXIHUaW1gagk4btE76oomc",yListener);
    }
}
