package com.tnecesoc.Control;

import com.tnecesoc.digitblock.DailySignInActivity;

/**
 * Created by Tnecesoc on 2016/6/3.
 */
public class TodaysBlock {

    private TodaysBlockAnimationControl animationControl;

    private DailySignInActivity activity;

    private SignInControl signInControl;

    private int currentPersistentDayCount;

    public TodaysBlock(DailySignInActivity activity) {
        this.activity = activity;
        signInControl = new SignInControl();
        currentPersistentDayCount = signInControl.getPersistentDayCount();

        animationControl = new TodaysBlockAnimationControl(activity);

        animationControl.generateNewBlockInPosWithValue(
                activity.todaysBlock_pos,
                currentPersistentDayCount
        );
    }

    public boolean signIn() {
        if (signInControl.signIn()) {
            currentPersistentDayCount++;
            animationControl.generateNewBlockInPosWithValue(
                    activity.todaysBlock_pos,
                    currentPersistentDayCount
            );
            return true;
        } else {
            return false;
        }
    }


}
