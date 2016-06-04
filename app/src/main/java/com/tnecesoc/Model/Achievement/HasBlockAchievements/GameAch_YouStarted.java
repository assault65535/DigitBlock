package com.tnecesoc.Model.Achievement.HasBlockAchievements;

import com.tnecesoc.Model.Consts.AchievementDetails;
import com.tnecesoc.Model.Consts.AchievementNames;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public class GameAch_YouStarted extends HasBlockAchievement {

    public GameAch_YouStarted(boolean isAchieved) {
        super(AchievementNames.YOU_STARTED, isAchieved, AchievementDetails.YOU_STARTED, 2);
    }

}
