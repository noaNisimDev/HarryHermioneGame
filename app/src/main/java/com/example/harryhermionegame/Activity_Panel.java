package com.example.harryhermionegame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Activity_Panel extends AppCompatActivity {

    private ImageView panel_IMG_hermione;
    private ProgressBar panel_PRB_simpleProgressBarHermione;
    private ImageButton panel_IMG_spellOfWater;
    private ImageButton panel_IMG_potionOfHermione;
    private ImageButton panel_IMG_wandOfHermione;

    private ImageView panel_IMG_harry;
    private ProgressBar panel_PRB_simpleProgressBarHarry;
    private ImageButton panel_IMG_spellOfFire;
    private ImageButton panel_IMG_potionOfHarry;
    private ImageButton panel_IMG_wandOfHarry;

    //values of powers hermione
    private int valueOfWater = 12;
    private int valueOfPotionOfHermione = 25;
    private int valueOfWandHermione = 50;

    //values of powers harry
    private int valueOfFire = 10;
    private int valueOfPotionOfHarry = 25;
    private int valueOfWandHarry = 50;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        ProgressBar progressBarHermione = (ProgressBar)findViewById(R.id.panel_PRB_simpleProgressBarHermione);
        progressBarHermione.setProgress(progressBarHermione.getMax());

        Log.d("Debug", "onCreate_spellOfWater after change progressbar: " + progressBarHermione.getProgress());
        ProgressBar progressBarHarry = (ProgressBar) findViewById(R.id.panel_PRB_simpleProgressBarHarry);
        progressBarHarry.setProgress(progressBarHarry.getMax());


        findViews();

        //callback - listener
        //hermione

        panel_IMG_spellOfWater.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                ProgressBar progressBar = (ProgressBar)findViewById(R.id.panel_PRB_simpleProgressBarHarry);
                Log.d("Debug", "onClick_spellOfWater: arrived");
               int currentHarryPrb = progressBar.getProgress();
                Log.d("Debug", "onClick_spellOfWater current progressbar: " + currentHarryPrb);

                progressBar.setProgress(currentHarryPrb - valueOfWater, true);

                Log.d("Debug", "onClick_spellOfWater after change progressbar: " + progressBar.getProgress());

            }
        });
        panel_IMG_potionOfHermione.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                ProgressBar progressBar = (ProgressBar)findViewById(R.id.panel_PRB_simpleProgressBarHarry);
                int currentHermionePro = progressBar.getProgress();
                progressBar.setProgress(currentHermionePro - valueOfPotionOfHermione, true );
            }
        });

        panel_IMG_wandOfHermione.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                ProgressBar progressBar = (ProgressBar)findViewById(R.id.panel_PRB_simpleProgressBarHarry);
                int currentHermionePro = progressBar.getProgress();
                progressBar.setProgress(currentHermionePro - valueOfWandHermione, true );
            }
        });

        //harry
        panel_IMG_spellOfFire.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                ProgressBar progressBar = (ProgressBar)findViewById(R.id.panel_PRB_simpleProgressBarHermione);
                int currentHarryPrb = progressBar.getProgress();
                progressBar.setProgress(currentHarryPrb - valueOfWater, true);
            }
        });


    }

    private void findViews() {

        //hermione R-id
        panel_IMG_hermione = findViewById(R.id.panel_IMG_hermione);
        panel_PRB_simpleProgressBarHermione = findViewById(R.id.panel_PRB_simpleProgressBarHermione);
        panel_IMG_spellOfWater = findViewById(R.id.panel_IMG_spellOfWater);
        panel_IMG_potionOfHermione = findViewById(R.id.panel_IMG_potionOfHermione);
        panel_IMG_wandOfHermione = findViewById(R.id.panel_IMG_wandOfHermione);
        //harry R-id
        panel_IMG_harry = findViewById(R.id.panel_IMG_harry);
        panel_PRB_simpleProgressBarHarry = findViewById(R.id.panel_PRB_simpleProgressBarHarry);
        panel_IMG_spellOfFire = findViewById(R.id.panel_IMG_spellOfFire);
        panel_IMG_potionOfHarry = findViewById(R.id.panel_IMG_potionOfHarry);
        panel_IMG_wandOfHarry = findViewById(R.id.panel_IMG_wandOfHarry);

    }


}