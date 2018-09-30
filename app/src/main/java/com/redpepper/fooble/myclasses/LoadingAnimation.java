package com.redpepper.fooble.myclasses;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;


import com.redpepper.fooble.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LoadingAnimation {
    private ImageView image;
    private int timeLap;
    private ArrayList<Integer> imagesArray;
    private int counter;
    private Timer timer;
    private Context context;

    public LoadingAnimation(ImageView image, int timeLap, Context context) {
        this.image = image;
        this.timeLap = timeLap;
        this.imagesArray = SetArraysDrawables();
        this.counter = 0;
        this.context = context;
    }

    public void playTheLoader(){

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        image.setBackgroundResource(imagesArray.get(counter));
                    }
                });

                if(counter == 5){
                    counter = 0;
                }else{
                    counter++;
                }

            }

            },0,this.timeLap);

    }

    public void stopTheLoader(){
        timer.cancel();
        timer.purge();
    }

    private ArrayList<Integer> SetArraysDrawables(){

        ArrayList<Integer> array = new ArrayList<>();

        array.add(R.drawable.loader1);
        array.add(R.drawable.loader2);
        array.add(R.drawable.loader3);
        array.add(R.drawable.loader4);
        array.add(R.drawable.loader5);
        array.add(R.drawable.loader6);

        return array;

    }


}
