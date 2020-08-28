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
import android.widget.Toast;

public class Activity_Panel extends AppCompatActivity {

    private ImageView panel_IMG_hermione;
    private ProgressBar panel_PRB_simpleProgressBarHermione;
    private ImageButton panel_IMG_spellOfWater ;
    private ImageButton panel_IMG_potionOfHermione;
    private ImageButton panel_IMG_wandOfHermione;

    private ImageView panel_IMG_harry;
    private ProgressBar panel_PRB_simpleProgressBarHarry;
    private ImageButton panel_IMG_spellOfFire;
    private ImageButton panel_IMG_potionOfHarry;
    private ImageButton panel_IMG_wandOfHarry;

    //values of powers hermione
    private int valueOfWater = 10;
    private int valueOfPotionOfHermione = 25;
    private int valueOfWandHermione = 50;

    //values of powers harry
    private int valueOfFire = 10;
    private int valueOfPotionOfHarry = 25;
    private int valueOfWandHarry = 50;

    private int currentHermionePrb = panel_PRB_simpleProgressBarHermione.getProgress();
    private  int currentHarryPrb = panel_PRB_simpleProgressBarHarry.getProgress();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        panel_PRB_simpleProgressBarHermione.setProgress(panel_PRB_simpleProgressBarHermione.getMax());

        Log.d("Debug", "onCreate_spellOfWater after change progressbar: " + panel_PRB_simpleProgressBarHermione.getProgress());
        ProgressBar progressBarHarry = panel_PRB_simpleProgressBarHermione;
        progressBarHarry.setProgress(progressBarHarry.getMax());


        findViews();

        //callback - listener
        //hermione

        panel_IMG_spellOfWater.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Log.d("Debug", "onClick_spellOfWater: arrived");
                Log.d("Debug", "onClick_spellOfWater current progressbar: " + currentHarryPrb);
                panel_PRB_simpleProgressBarHarry.setProgress(currentHarryPrb - valueOfWater, true);
                Log.d("Debug", "onClick_spellOfWater after change progressbar: " + panel_PRB_simpleProgressBarHarry.getProgress());
              //ToDo
                panel_IMG_spellOfWater.setClickable(false);
            }
        });
        panel_IMG_potionOfHermione.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                ProgressBar progressBar = (ProgressBar)findViewById(R.id.panel_PRB_simpleProgressBarHarry);
                progressBar.setProgress(currentHarryPrb - valueOfPotionOfHermione, true );
            }
        });

        panel_IMG_wandOfHermione.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                ProgressBar progressBar = (ProgressBar)findViewById(R.id.panel_PRB_simpleProgressBarHarry);
                progressBar.setProgress(currentHarryPrb - valueOfWandHermione, true );
            }
        });

        //harry
        panel_IMG_spellOfFire.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                panel_PRB_simpleProgressBarHermione.setProgress(currentHermionePrb - valueOfFire, true);
            }
        });

        panel_IMG_potionOfHarry.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                panel_PRB_simpleProgressBarHermione.setProgress(currentHermionePrb - valueOfPotionOfHarry, true);
            }
        });

        panel_IMG_wandOfHarry.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                panel_PRB_simpleProgressBarHermione.setProgress(currentHermionePrb - valueOfWandHarry, true);
            }
        });

        checkGameOver();
        lastLifeChangeStylePrb();

    }

    private void lastLifeChangeStylePrb(){
        if(panel_PRB_simpleProgressBarHarry.getProgress() <= 25){
            panel_PRB_simpleProgressBarHarry.setBackgroundColor(-65536 );
        }
        if(panel_PRB_simpleProgressBarHermione.getProgress() <= 25){
            panel_PRB_simpleProgressBarHermione.setBackgroundColor(-65536 );
        }

    }

    private void checkGameOver() {
        if(panel_PRB_simpleProgressBarHermione.getProgress() == 0){
            Toast.makeText(this, "Harry is won!", Toast.LENGTH_SHORT).show();
        }
        if(panel_PRB_simpleProgressBarHarry.getProgress() == 0){
            Toast.makeText(this, "Hermione is won!", Toast.LENGTH_SHORT).show();
        }

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