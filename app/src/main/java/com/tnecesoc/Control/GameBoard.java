package com.tnecesoc.Control;

import android.annotation.SuppressLint;
import android.widget.TextView;
import com.tnecesoc.digitblock.MainActivity;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Tnecesoc on 2016/5/28.
 */

@SuppressWarnings("Duplicates")
public class GameBoard {

    private MainActivity activity;

    private InGameAnimationControl inGameAnimationControl;

    private ScoreControl scoreControl;

    private ScoreFileControl scoreFileControl;

    private AchievementControl achievementControl;

    private AchievementFileControl achievementFileControl;

    int dimension = 4;

    public int[][] data = new int[dimension][dimension];

    public GameBoard(MainActivity activity) {
        this.activity = activity;
        inGameAnimationControl = new InGameAnimationControl(activity);
        scoreControl = new ScoreControl(this);
        scoreFileControl = new ScoreFileControl();
        achievementControl = new AchievementControl(activity, this);
        achievementFileControl = new AchievementFileControl();
    }

    public void initialize() {
        scoreFileControl.initHiscore();
        scoreControl.hiscoreCache = scoreFileControl.getHigherScoreData();
        achievementFileControl.initFile();
        achievementControl.cache = achievementFileControl.getAchievementRecord();
    }

    public boolean isFull() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (data[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFailure() {

        if(!isFull())
            return false;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension - 1; j++) {
                if (data[i][j] == data[i][j + 1]) {
                    return false;
                }
            }
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension - 1; j++) {
                if (data[j][i] == data[j + 1][i]) {
                    return false;
                }
            }
        }

        return true;
    }

    public void clear() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                data[i][j] = 0;
                inGameAnimationControl.resetCellIn(i, j);
            }
        }
        generateNewBlock();
    }

    public void generateNewBlock() {

        if (isFull())
            return;

        int x, y;

        do {
            x = (int) (dimension * Math.random());
            y = (int) (dimension * Math.random());
        } while (data[x][y] != 0);

        data[x][y] = 2;

        inGameAnimationControl.attachNewBlockIn(x, y);
    }

    public void moveDown() {
        for (int i = 0; i < dimension; i++) {
            for (int pivot = 0, j = pivot + 1; j < dimension; j++) {
                if (data[i][j] != 0) {

                    if (data[i][pivot] == data[i][j] || data[i][pivot] == 0) {

                        inGameAnimationControl.mergeBlock(i, pivot, data[i][pivot], i, j, data[i][j]);

                    } else {
                        inGameAnimationControl.mergeBlock(i, pivot + 1, data[i][pivot + 1], i, j, data[i][j]);

                        if (++pivot == j) {
                            continue;
                        }

                    }

                    data[i][pivot] += data[i][j];
                    data[i][j] = 0;

                }
            }
        }
        generateNewBlock();
    }

    public void moveUp() {
        for (int i = 0; i < dimension; i++) {
            for (int pivot = dimension - 1, j = pivot - 1; j >= 0; j--) {
                if (data[i][j] != 0) {

                    if (data[i][pivot] == data[i][j] || data[i][pivot] == 0) {

                        inGameAnimationControl.mergeBlock(i, pivot, data[i][pivot], i, j, data[i][j]);

                    } else {
                        inGameAnimationControl.mergeBlock(i, pivot - 1, data[i][pivot - 1], i, j, data[i][j]);

                        if (--pivot == j) {
                            continue;
                        }

                    }

                    data[i][pivot] += data[i][j];
                    data[i][j] = 0;

                }
            }
        }
        generateNewBlock();
    }

    public void moveRight() {
        for (int i = 0; i < dimension; i++) {
            for (int pivot = 0, j = pivot + 1; j < dimension; j++) {
                if (data[j][i] != 0) {

                    if (data[pivot][i] == data[j][i] || data[pivot][i] == 0) {

                        inGameAnimationControl.mergeBlock(pivot, i, data[pivot][i], j, i, data[j][i]);

                    } else {

                        inGameAnimationControl.mergeBlock(pivot + 1, i, data[pivot + 1][i], j, i, data[j][i]);

                        if (++pivot == j) {
                            continue;
                        }
                    }

                    data[pivot][i] += data[j][i];
                    data[j][i] = 0;

                }
            }
        }
        generateNewBlock();
    }

    public void moveLeft() {
        for (int i = 0; i < dimension; i++) {
            for (int pivot = dimension - 1, j = pivot - 1; j >= 0; j--) {
                if (data[j][i] != 0) {

                    if (data[pivot][i] == data[j][i] || data[pivot][i] == 0) {

                        inGameAnimationControl.mergeBlock(pivot, i, data[pivot][i], j, i, data[j][i]);

                    } else {

                        inGameAnimationControl.mergeBlock(pivot - 1, i, data[pivot - 1][i], j, i, data[j][i]);

                        if (--pivot == j) {
                            continue;
                        }

                    }

                    data[pivot][i] += data[j][i];
                    data[j][i] = 0;

                }
            }
        }
        generateNewBlock();
    }

    public void refreshAchievements() {

        achievementControl.investigateAchievements();

        achievementFileControl.updateAchievementData(achievementControl.getCache());
    }

    @SuppressLint("SetTextI18n")
    public void refreshScore(TextView nowScore, TextView HiScore) {

        BigInteger nowScoreNumber = scoreControl.reCalculateScore();

        nowScore.setText(nowScoreNumber.toString());
        HiScore.setText(scoreControl.getHigherScore(nowScoreNumber).toString());

    }

    public boolean saveHiscore() {

        BigInteger nowScore = scoreControl.reCalculateScore();

        if (!scoreControl.insertHiscore(nowScore))
            return false;

        scoreFileControl.updateHiscoreData(scoreControl.getHiscoreCache());

        return true;
    }

    public ArrayList<BigInteger> requestHiscore() {
        return scoreControl.getHiscoreCache();
    }

}
