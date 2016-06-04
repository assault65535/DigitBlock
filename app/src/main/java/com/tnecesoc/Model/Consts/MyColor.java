package com.tnecesoc.Model.Consts;

import android.annotation.SuppressLint;

import java.util.HashMap;

/**
 * Created by Tnecesoc on 2016/5/28.
 */
public class MyColor {

    // 0 -> 0, x -> log(x)
    public static final int BLOCK_COLOR[] = {
            0xFFCCC0B2,
            0xFFEFE4DA,
            0xFFEDE0C6,
            0xFFF3B174,
            0xFFF7955E,
            0xFFF87C5A,
            0xFFF95D32,
            0xFFEFCF6B,
            0xFFEECD58,
            0xFFEFC944,
            0xFFEEC632,
            0XFFEFC41F,
    };

    public static final int findColorByInt(int value) {
        if (value <= 0) {
            return BLOCK_COLOR[0];
        }

        if (value >= Math.pow(2, BLOCK_COLOR.length - 1)) {
            return BLOCK_COLOR[BLOCK_COLOR.length - 1];
        }

        return BLOCK_COLOR[(int) (Math.log(value)/Math.log(2))];
    }

    @SuppressLint("UseSparseArrays")
    public static final HashMap<Integer, Integer> findIntByColor = new HashMap<Integer, Integer>() {{
        put(BLOCK_COLOR[0], 0);
        put(BLOCK_COLOR[1], 1);
        put(BLOCK_COLOR[2], 2);
        put(BLOCK_COLOR[3], 3);
        put(BLOCK_COLOR[4], 4);
        put(BLOCK_COLOR[5], 5);
        put(BLOCK_COLOR[6], 6);
        put(BLOCK_COLOR[7], 7);
        put(BLOCK_COLOR[8], 8);
        put(BLOCK_COLOR[9], 9);
        put(BLOCK_COLOR[10], 10);
        put(BLOCK_COLOR[11], 11);
    }};
}
