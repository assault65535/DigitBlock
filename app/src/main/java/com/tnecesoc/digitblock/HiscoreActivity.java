package com.tnecesoc.digitblock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.tnecesoc.Control.ScoreFileControl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiscoreActivity extends AppCompatActivity {

    private ScoreFileControl scoreFileControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiscore);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);

        findViewById(R.id.hiscoreBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ListView scoreList = (ListView) findViewById(R.id.listView);
        scoreFileControl = new ScoreFileControl();

        assert scoreList != null;
        scoreList.setAdapter(
                new SimpleAdapter(
                        this,
                        givePresetData(),
                        R.layout.atom_score_list,
                        new String[]{"title", "info"},
                        new int[]{R.id.title, R.id.info}
                )
        );
    }

    private List<Map<String, Object>> givePresetData() {

        scoreFileControl.initHiscore();
        final ArrayList<BigInteger> rawData = scoreFileControl.getHigherScoreData();

        List<Map<String, Object>> ans = new ArrayList<Map<String, Object>>();

        int len = rawData.size();

        HashMap<String, Object> atom;

        for (int i = 0; i < len; i++) {

            atom = new HashMap<String, Object>() {{
                put("title", null);
                put("info", null);
            }};

            atom.put("title", "#" + (i + 1));
            atom.put("info", rawData.get(len - i - 1));

            ans.add(atom);

        }

        return ans;
    }
}
