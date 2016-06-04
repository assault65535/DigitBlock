package com.tnecesoc.Model.Achievement.HasBlockAchievements;

import com.tnecesoc.Model.Consts.AchievementDetails;
import com.tnecesoc.Model.Consts.AchievementNames;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public class GameAch_Block64Get extends HasBlockAchievement {

    public GameAch_Block64Get(boolean isAchieved) {
        super(AchievementNames.BLOCK64_GET, isAchieved, AchievementDetails.BLOCK64_GET, 64);
    }
}
