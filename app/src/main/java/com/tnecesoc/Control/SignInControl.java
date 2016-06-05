package com.tnecesoc.Control;

import android.annotation.SuppressLint;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tnecesoc on 2016/6/3.
 */
public class SignInControl {

    private SignInFileControl signInFileControl;

    private ArrayList<BigInteger> signInRecordCache;
    
    private BigInteger lastDate, nowDate;

    public SignInControl() {
        this.signInFileControl = new SignInFileControl();

        signInFileControl.initFile();

        signInRecordCache = signInFileControl.getSignInRecord();

        if (signInRecordCache.isEmpty()) {
            lastDate = BigInteger.valueOf(0);
        } else {
            lastDate = signInRecordCache.get(signInRecordCache.size() - 1);
        }

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        nowDate = new BigInteger(str);
        
    }

    public static boolean isContinuous(BigInteger last, BigInteger now) {

        return !(now.subtract(last).compareTo(BigInteger.valueOf(1)) == 1);

    }

    public boolean signIn() {

        if (lastDate.compareTo(nowDate) == 0) {
            return false;
        }
        
        signInRecordCache.add(nowDate);
        
        signInFileControl.updateSignInData(signInRecordCache);
        
        lastDate = nowDate;
        
        return true;
        
    }
    
    public int getPersistentDayCount() {

        if (!isContinuous(lastDate, nowDate)) {
            return 0;
        }

        int ans = 1;
        BigInteger prev = BigInteger.valueOf(0);

        for (BigInteger i : signInRecordCache) {

            if (ans == 1) {

                prev = i;

                ans++;

                continue;
            }

            if (isContinuous(prev, i)) {

                ans++;

                prev = i;

            } else {
                ans = 1;
            }
        }

        return ans;
    }


}
