package com.example.harryhermionegame;

import android.app.Application;
import android.util.Log;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("My app", "test my app");


        MySP.initHelper(this);
    }

}
