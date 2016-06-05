package com.tnecesoc.digitblock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tnecesoc.Control.GameBoard;
import com.tnecesoc.Views.Block;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private GameBoard gameBoard = new GameBoard(this);

    public Block[][] screen;

    private TextView nowScore, higherScore;

    public HashMap<Block, FrameLayout> screen_pos;

    public FrameLayout[][] theCellLocatedIn;

    //　used in onTouchEvent()
    private float x1, x2, y1, y2;

    // check if isFailure() returns true every 10 moves.
    private int moveCount;

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
                        gameBoard.moveUp();
                    } else {
                        gameBoard.moveDown();
                    }

                } else if (Math.abs(x1 - x2) > 200) {

                    if (x1 > x2) {
                        gameBoard.moveRight();
                    } else {
                        gameBoard.moveLeft();
                    }

                }

                if (++moveCount % 10 == 0) {

                    if (gameBoard.isFailure()) {

                        declareGameOver();

                    }
                }

                break;
        }

        gameBoard.refreshScore(nowScore, higherScore);

        gameBoard.refreshAchievements();

        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);

        screen = new Block[4][4];

        screen[0][0] = (Block) findViewById(R.id.b11);
        screen[0][1] = (Block) findViewById(R.id.b12);
        screen[0][2] = (Block) findViewById(R.id.b13);
        screen[0][3] = (Block) findViewById(R.id.b14);

        screen[1][0] = (Block) findViewById(R.id.b21);
        screen[1][1] = (Block) findViewById(R.id.b22);
        screen[1][2] = (Block) findViewById(R.id.b23);
        screen[1][3] = (Block) findViewById(R.id.b24);

        screen[2][0] = (Block) findViewById(R.id.b31);
        screen[2][1] = (Block) findViewById(R.id.b32);
        screen[2][2] = (Block) findViewById(R.id.b33);
        screen[2][3] = (Block) findViewById(R.id.b34);

        screen[3][0] = (Block) findViewById(R.id.b41);
        screen[3][1] = (Block) findViewById(R.id.b42);
        screen[3][2] = (Block) findViewById(R.id.b43);
        screen[3][3] = (Block) findViewById(R.id.b44);

        screen_pos = new HashMap<Block, FrameLayout>();

        screen_pos.put(screen[0][0], (FrameLayout) findViewById(R.id.b11_pos));
        screen_pos.put(screen[0][1], (FrameLayout) findViewById(R.id.b12_pos));
        screen_pos.put(screen[0][2], (FrameLayout) findViewById(R.id.b13_pos));
        screen_pos.put(screen[0][3], (FrameLayout) findViewById(R.id.b14_pos));

        screen_pos.put(screen[1][0], (FrameLayout) findViewById(R.id.b21_pos));
        screen_pos.put(screen[1][1], (FrameLayout) findViewById(R.id.b22_pos));
        screen_pos.put(screen[1][2], (FrameLayout) findViewById(R.id.b23_pos));
        screen_pos.put(screen[1][3], (FrameLayout) findViewById(R.id.b24_pos));

        screen_pos.put(screen[2][0], (FrameLayout) findViewById(R.id.b31_pos));
        screen_pos.put(screen[2][1], (FrameLayout) findViewById(R.id.b32_pos));
        screen_pos.put(screen[2][2], (FrameLayout) findViewById(R.id.b33_pos));
        screen_pos.put(screen[2][3], (FrameLayout) findViewById(R.id.b34_pos));

        screen_pos.put(screen[3][0], (FrameLayout) findViewById(R.id.b41_pos));
        screen_pos.put(screen[3][1], (FrameLayout) findViewById(R.id.b42_pos));
        screen_pos.put(screen[3][2], (FrameLayout) findViewById(R.id.b43_pos));
        screen_pos.put(screen[3][3], (FrameLayout) findViewById(R.id.b44_pos));

        theCellLocatedIn = new FrameLayout[4][4];

        theCellLocatedIn[0][0] = (FrameLayout) findViewById(R.id.b11_pos);
        theCellLocatedIn[0][1] = (FrameLayout) findViewById(R.id.b12_pos);
        theCellLocatedIn[0][2] = (FrameLayout) findViewById(R.id.b13_pos);
        theCellLocatedIn[0][3] = (FrameLayout) findViewById(R.id.b14_pos);

        theCellLocatedIn[1][0] = (FrameLayout) findViewById(R.id.b21_pos);
        theCellLocatedIn[1][1] = (FrameLayout) findViewById(R.id.b22_pos);
        theCellLocatedIn[1][2] = (FrameLayout) findViewById(R.id.b23_pos);
        theCellLocatedIn[1][3] = (FrameLayout) findViewById(R.id.b24_pos);

        theCellLocatedIn[2][0] = (FrameLayout) findViewById(R.id.b31_pos);
        theCellLocatedIn[2][1] = (FrameLayout) findViewById(R.id.b32_pos);
        theCellLocatedIn[2][2] = (FrameLayout) findViewById(R.id.b33_pos);
        theCellLocatedIn[2][3] = (FrameLayout) findViewById(R.id.b34_pos);

        theCellLocatedIn[3][0] = (FrameLayout) findViewById(R.id.b41_pos);
        theCellLocatedIn[3][1] = (FrameLayout) findViewById(R.id.b42_pos);
        theCellLocatedIn[3][2] = (FrameLayout) findViewById(R.id.b43_pos);
        theCellLocatedIn[3][3] = (FrameLayout) findViewById(R.id.b44_pos);

        nowScore = (TextView) findViewById(R.id.nowScore);
        higherScore = (TextView) findViewById(R.id.HiScore);

        gameBoard.generateNewBlock();

        gameBoard.initialize();

        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBoard.clear();
                gameBoard.refreshScore(nowScore, higherScore);
            }
        });



        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gameBoard.saveHiscore()) {
                    Toast.makeText(MainActivity.this, "New record!", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
    }

    void declareGameOver() {

        Toast.makeText(MainActivity.this, "Now it's a dead end. Please restart.", Toast.LENGTH_SHORT).show();

    }

}
