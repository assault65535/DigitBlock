package com.tnecesoc.Control;

import android.os.Environment;
import com.tnecesoc.Model.Achievement.GameAchievementList;
import com.tnecesoc.Model.Achievement.GameAchievement;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public class AchievementFileControl {

    private final String achievementDocName = "Achievement.txt";

    private final File achievementFile = new File(Environment.getExternalStorageDirectory(), achievementDocName);

    private final String hr = System.getProperty("line.separator");

    public void initFile() {

        if (achievementFile.exists()) {
            return;
        }

        try {

            FileOutputStream fout = new FileOutputStream(achievementFile);

            int len = GameAchievementList.getListSize();

            for (int i = 0; i < len; i++) {
                fout.write(Boolean.toString(false).getBytes());
                fout.write(hr.getBytes());
            }

            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void rewriteAchievementData() {

        if (achievementFile.exists()) {

            //noinspection ResultOfMethodCallIgnored
            achievementFile.delete();

        }

        initFile();

    }

    public void updateAchievementData(ArrayList<GameAchievement> record) {

        int len = record.size();

        ArrayList<Boolean> facts = new ArrayList<>();

        for (GameAchievement aRecord : record) {
            facts.add(aRecord.isFinished());
        }

        try {

            FileOutputStream fout = new FileOutputStream(achievementFile);

            for (Boolean fact : facts) {
                fout.write(fact.toString().getBytes());
                fout.write(hr.getBytes());
            }

            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public ArrayList<GameAchievement> getAchievementRecord() {

        ArrayList<Boolean> facts = new ArrayList<>();
        
        try {

            BufferedReader fin = new BufferedReader(new FileReader(achievementFile));
            String readline;

            while ((readline = fin.readLine()) != null) {

                facts.add(Boolean.valueOf(readline));

            }

            fin.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

        return GameAchievementList.generateListBasedOn(facts);

    }
    
    private boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }


}
