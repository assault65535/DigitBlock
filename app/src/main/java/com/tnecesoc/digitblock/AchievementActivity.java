package com.tnecesoc.digitblock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.tnecesoc.Control.AchievementFileControl;
import com.tnecesoc.Model.Achievement.GameAchievement;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AchievementActivity extends AppCompatActivity {

    ListView achievementList;

    AchievementFileControl achievementFileControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);

        findViewById(R.id.BackFromAchievementPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        achievementList = (ListView) findViewById(R.id.listView2);
        achievementFileControl = new AchievementFileControl();

        achievementList.setAdapter(new SimpleAdapter(
                this,
                givePresetData(),
                R.layout.atom_achievement_list,
                new String[]{"title", "info", "isAchieved"},
                new int[]{R.id.achievementListItem_Title, R.id.achievementListItem_Info, R.id.achievementListItem_IsAchieved}
        ));

    }

    List<Map<String, Object>> givePresetData() {


        final ArrayList<GameAchievement> rawData = achievementFileControl.getAchievementRecord();

        List<Map<String, Object>> ans = new ArrayList<Map<String, Object>>();

        int len = rawData.size();

        HashMap<String, Object> atom;

        for (int i = 0; i < len; i++) {

            atom = new HashMap<String, Object>() {{
                put("title", null);
                put("info", null);
                put("isAchieved", null);
            }};

            atom.put("title", rawData.get(i).getName());
            atom.put("info", rawData.get(i).getDetail());
            atom.put("isAchieved", rawData.get(i).isFinished()?"ACHIEVED":"NOT ACHIEVED");

            ans.add(atom);

        }

        return ans;
    }
}
