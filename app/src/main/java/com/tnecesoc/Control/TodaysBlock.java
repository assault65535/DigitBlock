package com.tnecesoc.Control;

import com.tnecesoc.digitblock.DailySignInActivity;

/**
 * Created by Tnecesoc on 2016/6/3.
 */
public class TodaysBlock {

    private TodaysBlockAnimationControl animationControl;

    private DailySignInActivity activity;

    private SignInControl signInControl;

    private int currentPreSistentDayCount;

    public TodaysBlock(DailySignInActivity activity) {
        this.activity = activity;
        signInControl = new SignInControl();
        currentPreSistentDayCount = signInControl.getPresistentDayCount();

        animationControl = new TodaysBlockAnimationControl(activity);

        animationControl.generateNewBlockInPosWithValue(
                activity.todaysBlock_pos,
                currentPreSistentDayCount
        );
    }

    public boolean signIn() {
        if (signInControl.signIn()) {
            currentPreSistentDayCount++;
            animationControl.generateNewBlockInPosWithValue(
                    activity.todaysBlock_pos,
                    currentPreSistentDayCount
            );
            return true;
        } else {
            return false;
        }
    }


}
