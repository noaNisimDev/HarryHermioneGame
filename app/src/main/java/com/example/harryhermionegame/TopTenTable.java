package com.example.harryhermionegame;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;


public class TopTenTable extends Fragment {
    private TableLayout table_TBL_highscores;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.topten_table, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        table_TBL_highscores = getActivity().findViewById(R.id.table_TBL_highscores);

        Gson gson = new Gson();
        TopTen topTen = gson.fromJson(MySP.getInstance().getString(MySP.KEYS.USER_HIGH_SCORE, null), TopTen.class);

        addRow("Winner", "#", true);
        if (topTen != null) {
            for (Score score : topTen.getScores()) {
                addRow(score.getWinner(), String.valueOf(score.getNumOfActions()), false);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addRow(String winner, String numOfMoves, boolean isHeadline) {
        TableRow row = new TableRow(getContext());
        TextView nameOfWinner = new TextView(getContext());
        TextView numberOfMoves = new TextView(getContext());
        TableRow.LayoutParams tableParams = new TableRow.LayoutParams();
        tableParams.setMargins(20, 20, 5, 5);

        Typeface typeface = getResources().getFont(R.font.cherry_swash_bold);
        nameOfWinner.setTypeface(typeface);
        numberOfMoves.setTypeface(typeface);

        nameOfWinner.setText(winner);
        numberOfMoves.setText(numOfMoves);

        nameOfWinner.setTextColor(Color.WHITE);
        numberOfMoves.setTextColor(Color.WHITE);
        if (isHeadline) {
            nameOfWinner.setTextSize(25);
            numberOfMoves.setTextSize(25);
        } else {
            nameOfWinner.setTextSize(18);
            numberOfMoves.setTextSize(18);
        }

        row.addView(nameOfWinner, tableParams);
        row.addView(numberOfMoves, tableParams);
        table_TBL_highscores.addView(row);
    }
}
