package com.tnecesoc.Model.Achievement.HasBlockAchievements;

import com.tnecesoc.Model.Consts.AchievementDetails;
import com.tnecesoc.Model.Consts.AchievementNames;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public class GameAch_Block256Get extends HasBlockAchievement {

    public GameAch_Block256Get(boolean isAchieved) {
        super(AchievementNames.BLOCK256_GET, isAchieved, AchievementDetails.BLOCK256_GET, 256);
    }
}
