package com.example.harryhermionegame;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Activity_Panel extends AppCompatActivity {

    // top ten
    private TopTen topTen;

    //Hermione
    private ImageView panel_IMG_hermione;
    private ProgressBar panel_PRB_simpleProgressBarHermione;
    private ImageButton panel_BTN_spellOfWater;
    private ImageButton panel_BTN_potionOfHermione;
    private ImageButton panel_BTN_wandOfHermione;
    private int counterHermione = 0;

    //Harry
    private ImageView panel_IMG_harry;
    private ProgressBar panel_PRB_simpleProgressBarHarry;
    private ImageButton panel_BTN_spellOfFire;
    private ImageButton panel_BTN_potionOfHarry;
    private ImageButton panel_BTN_wandOfHarry;
    private int counterHarry = 0;

    //timer
    private Chronometer panel_TIM_timer;
    private long lastPause = SystemClock.elapsedRealtime();

    //popup
    private ImageView popup_IMG_wizardWithCrown;

    //values of powers hermione
    private int valueOfWater = 10;
    private int valueOfPotionOfHermione = 25;
    private int valueOfWandHermione = 50;

    //values of powers harry
    private int valueOfFire = 10;
    private int valueOfPotionOfHarry = 25;
    private int valueOfWandHarry = 50;

    //style
    private int redColor = -65536;
    private int rangeOfLastLife = 25;

    //random - who is starting?
    private int hermioneStart = 1;
    private int harryStart = 2;
    private int randNum = 0;

    //timer
    TextView tvTimer;
    private long startTime, timeInMilliseconds = 0;
    Handler customHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_panel);

        topTen = new TopTen();

        if (MySP.getInstance().getString(MySP.KEYS.NUM_OF_GAME, null) == null) {
            MySP.getInstance().putString(MySP.KEYS.NUM_OF_GAME, "0");
        }


        findViews();

        randNum = hermioneStart + (int) (Math.random() * ((harryStart - hermioneStart) + 1));
        startGame();
        ProgressBar progressBarHermione = panel_PRB_simpleProgressBarHermione;
        progressBarHermione.setProgress(progressBarHermione.getMax());
        ProgressBar progressBarHarry = panel_PRB_simpleProgressBarHarry;
        progressBarHarry.setProgress(progressBarHarry.getMax());


        //callback - listener
        //hermione

        panel_BTN_spellOfWater.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int currentHarryPrb = panel_PRB_simpleProgressBarHarry.getProgress();
                panel_PRB_simpleProgressBarHarry.setProgress(currentHarryPrb - valueOfWater, true);
                updateColorPrb();
                disableHermioneButtons();
                enableHarryButtons();
                counterHermione++;
                checkGameOver();

            }
        });
        panel_BTN_potionOfHermione.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int currentHarryPrb = panel_PRB_simpleProgressBarHarry.getProgress();
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.panel_PRB_simpleProgressBarHarry);
                progressBar.setProgress(currentHarryPrb - valueOfPotionOfHermione, true);
                updateColorPrb();
                disableHermioneButtons();
                enableHarryButtons();
                counterHermione++;
                checkGameOver();
            }
        });

        panel_BTN_wandOfHermione.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int currentHarryPrb = panel_PRB_simpleProgressBarHarry.getProgress();
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.panel_PRB_simpleProgressBarHarry);
                progressBar.setProgress(currentHarryPrb - valueOfWandHermione, true);
                updateColorPrb();
                disableHermioneButtons();
                enableHarryButtons();
                counterHermione++;
                checkGameOver();
            }
        });

        //harry
        panel_BTN_spellOfFire.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int currentHermionePrb = panel_PRB_simpleProgressBarHermione.getProgress();
                panel_PRB_simpleProgressBarHermione.setProgress(currentHermionePrb - valueOfFire, true);
                updateColorPrb();
                disableHarryButtons();
                enableHermioneButtons();
                counterHarry++;
                checkGameOver();
            }
        });

        panel_BTN_potionOfHarry.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int currentHermionePrb = panel_PRB_simpleProgressBarHermione.getProgress();
                panel_PRB_simpleProgressBarHermione.setProgress(currentHermionePrb - valueOfPotionOfHarry, true);
                updateColorPrb();
                disableHarryButtons();
                enableHermioneButtons();
                counterHarry++;
                checkGameOver();
            }
        });

        panel_BTN_wandOfHarry.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int currentHermionePrb = panel_PRB_simpleProgressBarHermione.getProgress();
                panel_PRB_simpleProgressBarHermione.setProgress(currentHermionePrb - valueOfWandHarry, true);
                updateColorPrb();
                disableHarryButtons();
                enableHermioneButtons();
                counterHarry++;
                checkGameOver();

            }
        });


    }


    private void startGame() {
        if (randNum == hermioneStart) {
            disableHarryButtons();
            Toast hermioneStartMsg = Toast.makeText(this, "Hermione's turn", Toast.LENGTH_SHORT);
            hermioneStartMsg.show();
        }
        // i have only one more option
        else {
            disableHermioneButtons();
            Toast harryStartMsg = Toast.makeText(this, "Harry's turn", Toast.LENGTH_SHORT);
            harryStartMsg.show();
        }


        panel_TIM_timer.stop();

        if (lastPause != 0) {
            panel_TIM_timer.setBase(panel_TIM_timer.getBase() +
                    SystemClock.elapsedRealtime() - lastPause);
        } else {
            panel_TIM_timer.setBase(SystemClock.elapsedRealtime());
        }
        panel_TIM_timer.start();


    }

    private void updateColorPrb() {
        if (panel_PRB_simpleProgressBarHarry.getProgress() <= rangeOfLastLife) {
            panel_PRB_simpleProgressBarHarry.setBackgroundColor(redColor);
        }
        if (panel_PRB_simpleProgressBarHermione.getProgress() <= rangeOfLastLife) {
            panel_PRB_simpleProgressBarHermione.setBackgroundColor(redColor);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void checkGameOver() {
        boolean harryWon = false;
        boolean hermioneWon = false;

        if (panel_PRB_simpleProgressBarHermione.getProgress() == 0) {
            harryWon = true;
        }
        if (panel_PRB_simpleProgressBarHarry.getProgress() == 0) {
            hermioneWon = true;
        }
        if (harryWon || hermioneWon) {

            //disableAllButtons
            disableHarryButtons();
            disableHermioneButtons();

            int numOfGame = Integer.parseInt(MySP.getInstance().getString(MySP.KEYS.NUM_OF_GAME, "0"));
            Gson gson = new Gson();

            if (numOfGame != 0) {
                topTen = gson.fromJson(MySP.getInstance().getString(MySP.KEYS.USER_HIGH_SCORE, null), TopTen.class);
            }


            if (numOfGame < 10) {
                ++numOfGame;
                MySP.getInstance().putString(MySP.KEYS.NUM_OF_GAME, String.valueOf(numOfGame));
            }

            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.panel_LAY_relativeLayout);
            relativeLayout.setBackgroundResource(R.drawable.img_backgroug_color);

            final Dialog popupDialog = new Dialog(this);
            popupDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            popupDialog.setContentView(getLayoutInflater().inflate(R.layout.popup_layout, null));

            popup_IMG_wizardWithCrown = popupDialog.getWindow().findViewById(R.id.popup_IMG_wizardWithCrown);
            popupDialog.getWindow().findViewById(R.id.popup_BTN_gameOver).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupDialog.dismiss();
                }
            });

            long elapsedSecond = (SystemClock.elapsedRealtime() - panel_TIM_timer.getBase()) / 1000;

            if (harryWon) {
                ((ImageView) popup_IMG_wizardWithCrown).setImageResource(R.drawable.harrywon);
                //TODO- insert to shared preferences: who won, numOfActions
                //TODO - the preferences should be sorted by numOfActions
                //double lat, double lon, long timestamp, int numOfActions
                // Score score = new Score(32.131943, 34.855859, elapsedSecond, counterHarry, "Harry");

                if (numOfGame > 10 && topTen.getScores().lastIndexOf(topTen) > counterHarry) {
                    topTen.getScores().remove(topTen.getScores().lastIndexOf(topTen));
                    topTen.scores.add(new Score(32.131943, 34.855859, elapsedSecond, counterHarry, "Harry"));
                }
                if(numOfGame <= 10){
                    topTen.scores.add(new Score(32.131943, 34.855859, elapsedSecond, counterHarry, "Harry"));
                }

            }
            else {
                ((ImageView) popup_IMG_wizardWithCrown).setImageResource(R.drawable.hermionewon);

                if (numOfGame > 10 && topTen.getScores().lastIndexOf(topTen) > counterHermione) {
                    topTen.getScores().remove(topTen.getScores().lastIndexOf(topTen));
                    topTen.scores.add(new Score(32.131943, 34.855859, elapsedSecond, counterHermione, "Hermione"));
                }
                if(numOfGame <= 10){
                    topTen.scores.add(new Score(32.131943, 34.855859, elapsedSecond, counterHermione, "Hermione"));
                }

            }
            Collections.sort(topTen.scores);
            MySP.getInstance().putString(MySP.KEYS.USER_HIGH_SCORE, gson.toJson(topTen));

            panel_TIM_timer.stop();
            panel_TIM_timer.setBase(SystemClock.elapsedRealtime());
            lastPause = 0;
            popupDialog.show();
        }


    }


    private void disableHarryButtons() {
        //set all buttons to be disabled
        panel_BTN_wandOfHarry.setEnabled(false);
        panel_BTN_potionOfHarry.setEnabled(false);
        panel_BTN_spellOfFire.setEnabled(false);

        //change all buttons images to gray for UX
        ((ImageButton) panel_BTN_wandOfHarry).setImageResource(R.drawable.img_wandharry_gray);
        ((ImageButton) panel_BTN_potionOfHarry).setImageResource(R.drawable.img_potionofharry_gray);
        ((ImageButton) panel_BTN_spellOfFire).setImageResource(R.drawable.img_spelloffire_gray);
    }

    private void disableHermioneButtons() {
        //set all buttons to be disabled
        panel_BTN_wandOfHermione.setEnabled(false);
        panel_BTN_potionOfHermione.setEnabled(false);
        panel_BTN_spellOfWater.setEnabled(false);

        //change all buttons images to gray for UX
        ((ImageButton) panel_BTN_wandOfHermione).setImageResource(R.drawable.img_wandofhermione_gray);
        ((ImageButton) panel_BTN_potionOfHermione).setImageResource(R.drawable.img_potionofhermione_gray);
        ((ImageButton) panel_BTN_spellOfWater).setImageResource(R.drawable.img_spellofwater_gray);
    }

    private void enableHarryButtons() {

        //set all buttons to be disabled
        panel_BTN_wandOfHarry.setEnabled(true);
        panel_BTN_potionOfHarry.setEnabled(true);
        panel_BTN_spellOfFire.setEnabled(true);

        //change all buttons images to gray for UX
        ((ImageButton) panel_BTN_wandOfHarry).setImageResource(R.drawable.img_wandharry);
        ((ImageButton) panel_BTN_potionOfHarry).setImageResource(R.drawable.img_potionharry);
        ((ImageButton) panel_BTN_spellOfFire).setImageResource(R.drawable.img_fire);
    }

    private void enableHermioneButtons() {
        //set all buttons to be disabled
        panel_BTN_wandOfHermione.setEnabled(true);
        panel_BTN_potionOfHermione.setEnabled(true);
        panel_BTN_spellOfWater.setEnabled(true);

        //change all buttons images to gray for UX
        ((ImageButton) panel_BTN_wandOfHermione).setImageResource(R.drawable.img_wandhermione);
        ((ImageButton) panel_BTN_potionOfHermione).setImageResource(R.drawable.img_potionhermione);
        ((ImageButton) panel_BTN_spellOfWater).setImageResource(R.drawable.img_water);
    }


    private void findViews() {

        //hermione R-id
        panel_IMG_hermione = findViewById(R.id.panel_IMG_hermione);
        panel_PRB_simpleProgressBarHermione = findViewById(R.id.panel_PRB_simpleProgressBarHermione);
        panel_BTN_spellOfWater = findViewById(R.id.panel_BTN_spellOfWater);
        panel_BTN_potionOfHermione = findViewById(R.id.panel_BTN_potionOfHermione);
        panel_BTN_wandOfHermione = findViewById(R.id.panel_BTN_wandOfHermione);

        //harry R-id
        panel_IMG_harry = findViewById(R.id.panel_IMG_harry);
        panel_PRB_simpleProgressBarHarry = findViewById(R.id.panel_PRB_simpleProgressBarHarry);
        panel_BTN_spellOfFire = findViewById(R.id.panel_BTN_spellOfFire);
        panel_BTN_potionOfHarry = findViewById(R.id.panel_BTN_potionOfHarry);
        panel_BTN_wandOfHarry = findViewById(R.id.panel_BTN_wandOfHarry);

        //timer R-id
        panel_TIM_timer = findViewById(R.id.panel_TIM_timer);

    }


}