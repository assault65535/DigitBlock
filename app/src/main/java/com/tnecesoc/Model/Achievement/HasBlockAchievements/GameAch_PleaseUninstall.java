package com.tnecesoc.Model.Achievement.HasBlockAchievements;

import com.tnecesoc.Model.Consts.AchievementDetails;
import com.tnecesoc.Model.Consts.AchievementNames;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public class GameAch_PleaseUninstall extends HasBlockAchievement {

    public GameAch_PleaseUninstall(boolean isAchieved) {
        super(AchievementNames.BLOCK2048_GET, isAchieved, AchievementDetails.BLOCK2048_GET, 2048);
    }
}
