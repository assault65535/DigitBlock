package com.tnecesoc.Control;

import android.widget.Toast;
import com.tnecesoc.Model.Achievement.GameAchievement;
import com.tnecesoc.digitblock.MainActivity;

import java.util.ArrayList;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public class AchievementControl {

    private MainActivity activity;

    private GameBoard gameBoard;

    ArrayList<GameAchievement> cache;

    public AchievementControl(MainActivity activity, GameBoard gameBoard) {
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
