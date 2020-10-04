package com.example.harryhermionegame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class BackgroundMusic extends Service {
    MediaPlayer mp;
    static boolean keepMusicGoing = false;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        mp = MediaPlayer.create(this, R.raw.backgoundmusic);
        mp.setLooping(true);
    }

    public void onDestroy() {
        if (!keepMusicGoing) {
            mp.stop();
            mp.release();
            stopSelf();
            super.onDestroy();
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
