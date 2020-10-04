package com.example.harryhermionegame;

import java.util.ArrayList;

public class TopTen {

    ArrayList<Score> scores;

    public TopTen() {

        scores = new ArrayList<Score>();
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }


}
