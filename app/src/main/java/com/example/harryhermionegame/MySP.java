package com.example.harryhermionegame;

import android.content.Context;
import android.content.SharedPreferences;

public class MySP {

    private static MySP instance;
    private SharedPreferences prefs;


    public interface KEYS{

        public static final String USER_HIGH_SCORE = "USER_HIGH_SCORE";
        public static final String NUM_OF_GAME = "NUM_OF_GAME";

    }

    public static MySP getInstance(){
        return instance;
    }
    private MySP(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences("APP_SP_DB", Context.MODE_PRIVATE);
    }

    public static MySP initHelper(Context context){
        if (instance == null){
            instance = new MySP(context);
        }
        return instance;
    }


    public void putString(String key, String value){
        prefs.edit().putString(key, value).apply();
    }

    public String getString(String key, String def){
        return prefs.getString(key, def);
    }



}
