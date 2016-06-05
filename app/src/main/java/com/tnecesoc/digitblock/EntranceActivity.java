package com.tnecesoc.digitblock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class EntranceActivity extends AppCompatActivity {

    float x1, x2, y1, y2; // used in onTouchEvent()

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                if (Math.abs(y1 - y2) > 500) {

                    if (y1 > y2) {
                        //up
                    } else {
                        //down
                    }

                } else if (Math.abs(x1 - x2) > 200) {

                    if (x1 > x2) {
                        //left
                        toNewGame();

                    } else {
                        //right
                    }

                }

                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);

        findViewById(R.id.newGameButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNewGame();
            }
        });

        findViewById(R.id.layout_hiscore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(HiscoreActivity.class);
            }
        });

        findViewById(R.id.layout_achievement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(AchievementActivity.class);
            }
        });

        findViewById(R.id.layout_dailySignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(DailySignInActivity.class);
            }
        });

        findViewById(R.id.layout_quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void toNewGame() {
        startActivity(new Intent(EntranceActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
    }

    private void toActivity(Class activityClass) {
        startActivity(new Intent(EntranceActivity.this, activityClass));
    }
}
