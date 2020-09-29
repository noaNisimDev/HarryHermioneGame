package com.example.harryhermionegame;

import java.util.Comparator;

public class Score implements Comparable<Score> {

    private double lat = 0.0;
    private double lon = 0.0;
    private long timestamp = 0;
    private int numOfActions = 99;
    private String winner = "";


    public Score(double lat, double lon, long timestamp, int numOfActions, String winner){

        this.lat = lat;
        this.lon = lon;
        this.timestamp = timestamp;
        this.numOfActions = numOfActions;
        this.winner = winner;
    }

    private double getLat(){
        return lat;
    }

    public Score setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Score setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Score setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getNumOfActions() {
        return numOfActions;
    }

    public Score setNumOfActions(int numOfActions) {
        this.numOfActions = numOfActions;
        return this;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }


    @Override
    public int compareTo(Score score) {
        if(this.numOfActions > score.numOfActions)
            return 1;
        else if(this.numOfActions == score.numOfActions)
            return 0;
        else
            return -1;
    }
}
