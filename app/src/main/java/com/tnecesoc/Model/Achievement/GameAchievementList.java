package com.tnecesoc.Model.Achievement;

import com.tnecesoc.Model.Achievement.HasBlockAchievements.*;
import com.tnecesoc.Model.Consts.AchievementNames;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tnecesoc on 2016/6/4.
 */

public class GameAchievementList {


    /**
     * corespondent relations:
     * 0: YouStarted
     * 1: YouMoved
     * 2: Block8Get
     * 3: Block16Get
     * ...
     * 9: Block1024Get
     * 10: PleaseUninstall
     * */
    public static ArrayList<GameAchievement> generateListBasedOn(ArrayList<Boolean> rawFacts) {
        ArrayList<GameAchievement> ans = new ArrayList<>();

        int realLength = 11;

        Boolean[] facts = new Boolean[realLength];
        int len = rawFacts.size();

        for (int i = 0; i < realLength; i++) {
            if (i < len) {
                facts[i] = rawFacts.get(i);
            } else {
                facts[i] = false;
            }
        }

        ans.add(new GameAch_YouStarted(facts[0]));
        ans.add(new GameAch_YouMoved(facts[1]));
        ans.add(new GameAch_Block8Get(facts[2]));
        ans.add(new GameAch_Block16Get(facts[3]));
        ans.add(new GameAch_Block32Get(facts[4]));
        ans.add(new GameAch_Block64Get(facts[5]));
        ans.add(new GameAch_Block128Get(facts[6]));
        ans.add(new GameAch_Block256Get(facts[7]));
        ans.add(new GameAch_Block512Get(facts[8]));
        ans.add(new GameAch_Block1024Get(facts[9]));
        ans.add(new GameAch_PleaseUninstall(facts[10]));

        return ans;

    }

    public static HashMap<Class, Integer> findIdByClass = new HashMap<Class, Integer>() {{
        put(GameAch_YouStarted.class, 0);
        put(GameAch_YouMoved.class, 1);
        put(GameAch_Block8Get.class, 2);
        put(GameAch_Block16Get.class, 3);
        put(GameAch_Block32Get.class, 4);
        put(GameAch_Block64Get.class, 5);
        put(GameAch_Block128Get.class, 6);
        put(GameAch_Block256Get.class, 7);
        put(GameAch_Block512Get.class, 8);
        put(GameAch_Block1024Get.class, 9);
        put(GameAch_PleaseUninstall.class, 10);
    }};

    public static ArrayList<String> findNameById = new ArrayList<String>() {{
        add(AchievementNames.YOU_STARTED);
        add(AchievementNames.YOU_MOVED);
        add(AchievementNames.BLOCK8_GET);
        add(AchievementNames.BLOCK16_GET);
        add(AchievementNames.BLOCK32_GET);
        add(AchievementNames.BLOCK64_GET);
        add(AchievementNames.BLOCK128_GET);
        add(AchievementNames.BLOCK256_GET);
        add(AchievementNames.BLOCK512_GET);
        add(AchievementNames.BLOCK1024_GET);
        add(AchievementNames.BLOCK2048_GET);
    }};

    public static int getListSize() {
        return findIdByClass.size();
    }

}
