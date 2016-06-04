package com.tnecesoc.Control;

import android.annotation.SuppressLint;
import android.os.Environment;

import java.io.*;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tnecesoc on 2016/6/3.
 */
public class SignInFileControl {

    private final String signInDocName = "SignInRecord.txt";

    private final File signInFile = new File(Environment.getExternalStorageDirectory(), signInDocName);

    private final String hr = System.getProperty("line.separator");

    public void initFile() {

        if (signInFile.exists()) {
            return;
        }

        try {

            FileOutputStream fout = new FileOutputStream(signInFile);

            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void rewriteSignInData() {

        if (signInFile.exists()) {

            //noinspection ResultOfMethodCallIgnored
            signInFile.delete();

        }

        try {

            FileOutputStream fout = new FileOutputStream(signInFile);

            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateSignInData(ArrayList<BigInteger> dates) {

        try {

            FileOutputStream fout = new FileOutputStream(signInFile);

            for (BigInteger date : dates) {
                fout.write(date.toString().getBytes());
                fout.write(hr.getBytes());
            }

            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public ArrayList<BigInteger> getSignInRecord() {

        ArrayList<BigInteger> ans = new ArrayList<>();


        try {

            BufferedReader fin = new BufferedReader(new FileReader(signInFile));
            String readline;
            BigInteger now;

            while ((readline = fin.readLine()) != null) {

                now = new BigInteger(readline);

                ans.add(now);

            }

            fin.close();

            return ans;

        } catch (IOException e) {
            e.printStackTrace();
            return ans;
        }

    }



    private boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

}
