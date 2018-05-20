package com.example.cecil.database2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.example.cecil.database2.R.id.fragment_container;

public class Info extends YouTubeBaseActivity  {
    YouTubePlayerView youTubePlayerView;
    YouTubePlayerView youTubePlayerView1;
    YouTubePlayerView youTubePlayerView2;
    YouTubePlayerView youTubePlayerView3;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button BnTilbageIn;

    YouTubePlayer.OnInitializedListener onInitializedListener;
    YouTubePlayer.OnInitializedListener onInitializedListener1;
    YouTubePlayer.OnInitializedListener onInitializedListener2;
    YouTubePlayer.OnInitializedListener onInitializedListener3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        button = (Button)findViewById(R.id.bn_play);
        button1 = (Button)findViewById(R.id.bn_play1);
        button2 = (Button)findViewById(R.id.bn_play2);
        button3 = (Button)findViewById(R.id.bn_play3);

        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_player_view);
        youTubePlayerView1 = (YouTubePlayerView)findViewById(R.id.youtube_player_view1);
        youTubePlayerView2 = (YouTubePlayerView)findViewById(R.id.youtube_player_view2);
        youTubePlayerView3 = (YouTubePlayerView)findViewById(R.id.youtube_player_view3);

        //Video1
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("CMGJFc5Va74");

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

        };



        Log.e("Debug", "FirstBtnListener");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                youTubePlayerView.initialize(PlayerConfig.API_KEY, onInitializedListener);
            }
        });


        //Video2
        onInitializedListener1 = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("KX0HcZRQIeY");

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

        };

        Log.e("Debug", "SecondBtnListener");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView1.initialize(PlayerConfig.API_KEY, onInitializedListener1);
            }
        });


        //Video3
        onInitializedListener2 = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("vNBS4EpcEsg");

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

        };

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView2.initialize(PlayerConfig.API_KEY, onInitializedListener2);
            }
        });

        //Video4
        onInitializedListener3 = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("AD63xH5dU9I");

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

        };

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView3.initialize(PlayerConfig.API_KEY, onInitializedListener3);
            }
        });

    }


}

