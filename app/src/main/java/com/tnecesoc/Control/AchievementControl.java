package com.tnecesoc.Control;

import android.app.Activity;
import android.widget.Toast;
import com.tnecesoc.Model.Achievement.GameAchievement;

import java.util.ArrayList;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public class AchievementControl {

    Activity activity;

    private GameBoard gameBoard;

    ArrayList<GameAchievement> cache;

    public AchievementControl(Activity activity, GameBoard gameBoard) {
        this.activity = activity;
        this.gameBoard = gameBoard;
    }

    public void investigateAchievements() {

        for (GameAchievement aCache : cache) {

            boolean initialState = aCache.isFinished();

            aCache.investigate(gameBoard.data);

            boolean finalState = aCache.isFinished();

            if (initialState != finalState && finalState)
               Toast.makeText(activity, "Achievement [" + aCache.getName() + "] Achieved.", Toast.LENGTH_SHORT).show();
        }

    }

    public ArrayList<GameAchievement> getCache() {
        return cache;
    }
}
