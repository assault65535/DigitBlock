package com.tnecesoc.Model.Achievement.HasBlockAchievements;

import com.tnecesoc.Model.Consts.AchievementDetails;
import com.tnecesoc.Model.Consts.AchievementNames;

/**
 * Created by Tnecesoc on 2016/6/4.
 */
public class GameAch_YouMoved extends HasBlockAchievement {

    public GameAch_YouMoved(boolean isAchieved) {
        super(AchievementNames.YOU_MOVED, isAchieved, AchievementDetails.YOU_MOVED, 4);
    }
}
