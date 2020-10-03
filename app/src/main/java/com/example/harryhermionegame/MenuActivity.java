package com.example.harryhermionegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

   private Button menu_BTN_startGame;
   private Button menu_BTN_topTen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this, BackgroundMusic.class));

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_menu);

        findViews();

        menu_BTN_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(Activity_Panel.class);
            }
        });
        menu_BTN_topTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(ActivityTopten.class);
            }
        });



    }

    private void launchActivity(Class activity_panelClass) {

        Intent intent = new Intent(this, activity_panelClass);
        startActivity(intent);
    }

    private void findViews(){
        menu_BTN_startGame = findViewById(R.id.menu_BTN_startGame);
        menu_BTN_topTen = findViewById(R.id.menu_BTN_topTen);
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, BackgroundMusic.class));
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        stopService(new Intent(this, BackgroundMusic.class));
        super.onPause();
    }

    @Override
    protected void onResume() {
        startService(new Intent(this, BackgroundMusic.class));
        super.onResume();
    }
}