package com.example.harryhermionegame;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Panel extends AppCompatActivity {

    private ImageView panel_IMG_hermione;
    private ProgressBar panel_PRB_simpleProgressBarHermione;
    private ImageButton panel_BTN_spellOfWater;
    private ImageButton panel_BTN_potionOfHermione;
    private ImageButton panel_BTN_wandOfHermione;

    private ImageView panel_IMG_harry;
    private ProgressBar panel_PRB_simpleProgressBarHarry;
    private ImageButton panel_BTN_spellOfFire;
    private ImageButton panel_BTN_potionOfHarry;
    private ImageButton panel_BTN_wandOfHarry;

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_panel);

        findViews();

        //ladies first - hermione starts first
        disableHarryButtons();
        Toast hermioneStartMsg = Toast.makeText(this,"Hermione's turn", Toast.LENGTH_SHORT);
        hermioneStartMsg.show();

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
                checkGameOver();

            }
        });
        panel_BTN_potionOfHermione.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int currentHarryPrb = panel_PRB_simpleProgressBarHarry.getProgress();
                ProgressBar progressBar = (ProgressBar)findViewById(R.id.panel_PRB_simpleProgressBarHarry);
                progressBar.setProgress(currentHarryPrb - valueOfPotionOfHermione, true );
                updateColorPrb();
                disableHermioneButtons();
                enableHarryButtons();
                checkGameOver();
            }
        });

        panel_BTN_wandOfHermione.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int currentHarryPrb = panel_PRB_simpleProgressBarHarry.getProgress();
                ProgressBar progressBar = (ProgressBar)findViewById(R.id.panel_PRB_simpleProgressBarHarry);
                progressBar.setProgress(currentHarryPrb - valueOfWandHermione, true );
                updateColorPrb();
                disableHermioneButtons();
                enableHarryButtons();
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
                checkGameOver();

            }
        });
    }

    private void updateColorPrb(){
        if(panel_PRB_simpleProgressBarHarry.getProgress() <= rangeOfLastLife){
            panel_PRB_simpleProgressBarHarry.setBackgroundColor(redColor);
        }
        if(panel_PRB_simpleProgressBarHermione.getProgress() <= rangeOfLastLife){
            panel_PRB_simpleProgressBarHermione.setBackgroundColor(redColor);
        }
    }

    private void checkGameOver() {
        boolean harryWon = false;
        boolean hermioneWon = false;
        if(panel_PRB_simpleProgressBarHermione.getProgress() == 0){
            harryWon = true;
        }
        if(panel_PRB_simpleProgressBarHarry.getProgress() == 0){
            hermioneWon = true;
        }
        if(harryWon || hermioneWon){

            //disableAllButtons
            disableHarryButtons();
            disableHermioneButtons();

            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.panel_LAY_relativeLayout);
            relativeLayout.setBackgroundResource(R.drawable.img_backgroug_color);

            final Dialog popupDialog = new Dialog(this);
            popupDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            popupDialog.setContentView(getLayoutInflater().inflate(R.layout.popup_layout, null));

            popup_IMG_wizardWithCrown = popupDialog.getWindow().findViewById(R.id.popup_IMG_wizardWithCrown);
            popupDialog.getWindow().findViewById(R.id.popup_BTN_newGame).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupDialog.dismiss();
                }
            });

            if(harryWon) {
                ((ImageView) popup_IMG_wizardWithCrown).setImageResource(R.drawable.harrywon);
            }
            else {
                ((ImageView) popup_IMG_wizardWithCrown).setImageResource(R.drawable.hermionewon);
            }
            popupDialog.show();
        }

    }


    private void disableHarryButtons(){
        //set all buttons to be disabled
        panel_BTN_wandOfHarry.setEnabled(false);
        panel_BTN_potionOfHarry.setEnabled(false);
        panel_BTN_spellOfFire.setEnabled(false);

        //change all buttons images to gray for UX
        ((ImageButton) panel_BTN_wandOfHarry).setImageResource(R.drawable.img_wandharry_gray);
        ((ImageButton) panel_BTN_potionOfHarry).setImageResource(R.drawable.img_potionofharry_gray);
        ((ImageButton) panel_BTN_spellOfFire).setImageResource(R.drawable.img_spelloffire_gray);
    }

    private void disableHermioneButtons(){
        //set all buttons to be disabled
        panel_BTN_wandOfHermione.setEnabled(false);
        panel_BTN_potionOfHermione.setEnabled(false);
        panel_BTN_spellOfWater.setEnabled(false);

        //change all buttons images to gray for UX
        ((ImageButton) panel_BTN_wandOfHermione).setImageResource(R.drawable.img_wandofhermione_gray);
        ((ImageButton) panel_BTN_potionOfHermione).setImageResource(R.drawable.img_potionofhermione_gray);
        ((ImageButton) panel_BTN_spellOfWater).setImageResource(R.drawable.img_spellofwater_gray);
    }

    private void enableHarryButtons(){

        //set all buttons to be disabled
        panel_BTN_wandOfHarry.setEnabled(true);
        panel_BTN_potionOfHarry.setEnabled(true);
        panel_BTN_spellOfFire.setEnabled(true);

        //change all buttons images to gray for UX
        ((ImageButton) panel_BTN_wandOfHarry).setImageResource(R.drawable.img_wandharry);
        ((ImageButton) panel_BTN_potionOfHarry).setImageResource(R.drawable.img_potionharry);
        ((ImageButton) panel_BTN_spellOfFire).setImageResource(R.drawable.img_fire);
    }

    private void enableHermioneButtons(){
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

    }


}