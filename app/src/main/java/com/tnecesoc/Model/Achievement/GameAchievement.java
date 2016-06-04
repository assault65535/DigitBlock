package com.tnecesoc.Model.Achievement;

import com.tnecesoc.Views.Block;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public abstract class GameAchievement implements Achievement {

    private String name;
    private String detail;
    private boolean isAchieved;

    public GameAchievement(String name, boolean isAchieved, String detail) {
        this.name = name;
        this.isAchieved = isAchieved;
        this.detail = detail;
    }

    @Override
    public String getDetail() {
        return detail;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isFinished() {
        return isAchieved;
    }

    @Override
    public void setFinished(Boolean isFinished) {

        this.isAchieved = isFinished;
    }

    abstract public void investigate(int[][] data);
}
