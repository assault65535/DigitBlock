package com.tnecesoc.Model.Achievement.HasBlockAchievements;

import com.tnecesoc.Model.Achievement.GameAchievement;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public abstract class HasBlockAchievement extends GameAchievement {

    private int base;

    public HasBlockAchievement(String name, boolean isAchieved, String detail, int base) {
        super(name, isAchieved, detail);
        this.base = base;
    }

    @Override
    public String toString() {
        return getName() + isFinished().toString();
    }

    @Override
    public void investigate(int[][] data) {
        if (isFinished()) {
            return;
        }

        int dimension = data.length;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (data[i][j] >= base) {
                    setFinished(true);
                    return;
                }
            }
        }
    }
}
