package com.tnecesoc.Control;

import android.content.SharedPreferences;
import android.os.Environment;
import com.tnecesoc.Model.Achievement.GameAchievementList;
import com.tnecesoc.Model.Achievement.GameAchievement;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public class AchievementFileControl {

    private SharedPreferences achievementPreference;

    public AchievementFileControl(SharedPreferences achievementPreference) {
        this.achievementPreference = achievementPreference;
    }

    public void initFile() {

    }

    public void updateAchievementData(ArrayList<GameAchievement> record) {

        SharedPreferences.Editor editor = achievementPreference.edit();

        ArrayList<Boolean> facts = new ArrayList<>();

        for (GameAchievement aRecord : record) {

            facts.add(aRecord.isFinished());

            editor.putBoolean(aRecord.getName(), aRecord.isFinished());

        }

        editor.apply();

    }

    public ArrayList<GameAchievement> getAchievementRecord() {

        ArrayList<Boolean> facts = new ArrayList<>();

        for (String name : GameAchievementList.achievementNameList) {

            facts.add(achievementPreference.getBoolean(name, false));

        }

        return GameAchievementList.generateListBasedOn(facts);

    }

}
