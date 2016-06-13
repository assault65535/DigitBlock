package com.tnecesoc.Control;

import android.content.SharedPreferences;
import android.os.Environment;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Tnecesoc on 2016/6/3.
 */
public class ScoreFileControl {

    private final int base = 10;

    private SharedPreferences scorePreferences;

    public ScoreFileControl(SharedPreferences scorePreferences) {
        this.scorePreferences = scorePreferences;
    }

    public void initHiscore() {
        BigInteger scoreAdapter;
        BigInteger baseScoreFactor = BigInteger.valueOf(3000);

        SharedPreferences.Editor editor = scorePreferences.edit();

        if (!scorePreferences.getString("#1", "0").equals("0"))
            return;

        for (int i = 0; i < base; i++) {

            scoreAdapter = BigInteger.valueOf(i + 1);

            editor.putString("#" + (i + 1), scoreAdapter.multiply(baseScoreFactor).toString());

        }

        editor.apply();
    }

    public ArrayList<BigInteger> getHigherScoreData() {

        ArrayList<BigInteger> ans = new ArrayList<>();

        for (int i = 0; i < base; i++) {

            String value = scorePreferences.getString("#" + (i + 1), "0");

            ans.add(new BigInteger(value));

        }

        return ans;
    }

    void updateHiscoreData(ArrayList<BigInteger> scores) {

        SharedPreferences.Editor editor = scorePreferences.edit();

        for (int i = 0; i < base; i++) {
            editor.putString("#" + (i + 1), scores.get(i).toString());
        }

        editor.apply();
    }

}
