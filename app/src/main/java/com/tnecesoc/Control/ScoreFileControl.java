package com.tnecesoc.Control;

import android.os.Environment;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Tnecesoc on 2016/6/3.
 */
public class ScoreFileControl {

    private final String hiscoreDocName = "Hiscores.txt";

    private final File scoreFile = new File(Environment.getExternalStorageDirectory(), hiscoreDocName);

    private final int base = 10;

    public void initHiscore() {
        BigInteger scoreAdapter;
        BigInteger baseScoreFactor = BigInteger.valueOf(3000);

        if (scoreFile.exists())
            return;

        try {

            FileOutputStream fout = new FileOutputStream(scoreFile);
            String hr = System.getProperty("line.separator");

            for (int i = 0; i < base; i++) {

                scoreAdapter = BigInteger.valueOf(i + 1);

                fout.write(scoreAdapter.multiply(baseScoreFactor).toString().getBytes());
                fout.write(hr.getBytes());
            }

            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BigInteger> getHigherScoreData() {

        ArrayList<BigInteger> ans = new ArrayList<>();


        try {
            BufferedReader fin = new BufferedReader(new FileReader(scoreFile));
            String readline;

            while ((readline = fin.readLine()) != null) {
                ans.add(new BigInteger(readline));;
            }

            fin.close();

            return ans;

        } catch (IOException e) {
            e.printStackTrace();
            return ans;
        }
    }

    void updateHiscoreData(ArrayList<BigInteger> scores) {

        int len = scores.size();

        try {

            FileOutputStream fout = new FileOutputStream(scoreFile);
            String hr = System.getProperty("line.separator");

            for (int i = 0; i < len; i++) {
                fout.write(scores.get(i).toString().getBytes());
                fout.write(hr.getBytes());
            }

            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            sdpath = "喵喵喵喵喵?";
        }
        return sdpath;

    }

    String getDefaultFilePath() {
        String filepath = "";
        File file = new File(Environment.getExternalStorageDirectory(), hiscoreDocName);
        if (file.exists()) {
            filepath = file.getAbsolutePath();
        } else {
            filepath = "喵喵喵喵喵?";
        }
        return filepath;
    }

}
