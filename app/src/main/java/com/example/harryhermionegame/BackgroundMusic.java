package com.example.harryhermionegame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundMusic extends Service {
    MediaPlayer mp;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate()
    {
        mp = MediaPlayer.create(this, R.raw.backgoundmusic);
        mp.setLooping(true);
    }
    public void onDestroy()
    {
        mp.stop();
        mp.release();
        stopSelf();
        super.onDestroy();
    }



    public void onStart(Intent intent,int startid){

        mp.start();
    }
}
