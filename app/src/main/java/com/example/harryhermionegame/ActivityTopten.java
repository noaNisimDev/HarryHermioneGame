package com.example.harryhermionegame;


import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.FragmentActivity;


public class ActivityTopten extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_topten);
    }

    @Override
    protected void onStop() {
        if (!BackgroundMusic.keepMusicGoing)
            stopService(new Intent(this, BackgroundMusic.class));
        super.onStop();
        BackgroundMusic.keepMusicGoing = false;
    }

    @Override
    protected void onResume() {
        startService(new Intent(this, BackgroundMusic.class));
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        BackgroundMusic.keepMusicGoing = true;
        super.onBackPressed();
    }
}