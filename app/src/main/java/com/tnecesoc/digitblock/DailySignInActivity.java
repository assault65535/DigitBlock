package com.tnecesoc.digitblock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.tnecesoc.Control.TodaysBlock;
import com.tnecesoc.Views.Block;

public class DailySignInActivity extends AppCompatActivity {

    public FrameLayout todaysBlock_pos;

    private TodaysBlock todaysBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_sign_in);

        Block tmp = (Block) findViewById(R.id.blockTodayBackground);
        tmp.resize(this);

        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);

        todaysBlock_pos = (FrameLayout) findViewById(R.id.blockToday);

        todaysBlock = new TodaysBlock(this);

        findViewById(R.id.signInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todaysBlock.signIn()) {
                    Toast.makeText(DailySignInActivity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DailySignInActivity.this, "You have already signed in!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.signInPageBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
