package com.example.harryhermionegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class ActivityTopten extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_topten);

        //Activity_Panel.mySp.putString(MySP.KEYS.USER_DATA, "1 : ");
    }
}