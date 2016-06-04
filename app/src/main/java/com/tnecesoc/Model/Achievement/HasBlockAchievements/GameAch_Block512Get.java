package com.tnecesoc.Model.Achievement.HasBlockAchievements;

import com.tnecesoc.Model.Consts.AchievementDetails;
import com.tnecesoc.Model.Consts.AchievementNames;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public class GameAch_Block512Get extends HasBlockAchievement {

    public GameAch_Block512Get(boolean isAchieved) {
        super(AchievementNames.BLOCK512_GET, isAchieved, AchievementDetails.BLOCK512_GET, 512);
    }
}
