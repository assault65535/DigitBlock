package com.tnecesoc.Control;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Tnecesoc on 2016/6/2.
 */

public class ScoreControl {

    private GameBoard gameBoard;

    ArrayList<BigInteger> hiscoreCache;

    ScoreControl(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public ArrayList<BigInteger> getHiscoreCache() {
        return hiscoreCache;
    }

    public BigInteger getHigherScore(BigInteger score) {

        for (BigInteger i : hiscoreCache) {
            if (score.compareTo(i) == -1) {
                return i;
            }
        }

        return score;
    }

    public boolean insertHiscore(BigInteger score) {

        int len = hiscoreCache.size();
        int index = 0;

        ArrayList<BigInteger> result = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            if (score.compareTo(hiscoreCache.get(i)) == -1) {
                index = i;
                break;
            }
        }

        if (index == 0) {
            return false;
        }

        for (int i = 1; i < len; i++) {

            if (i == index) {
                result.add(score);
            }

            result.add(hiscoreCache.get(i));
        }

        hiscoreCache = result;

        return true;
    }

    public BigInteger reCalculateScore() {

        BigInteger ans = new BigInteger("0");
        BigInteger now;

        for (int i = 0; i < gameBoard.dimension; i++) {
            for (int j = 0; j < gameBoard.dimension; j++) {
                now = BigInteger.valueOf(gameBoard.data[i][j]);
                now = now.multiply(now);
                ans = ans.add(now);
            }
        }

        return ans;
    }
}
