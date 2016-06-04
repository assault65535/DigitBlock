package com.tnecesoc.Control;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import com.tnecesoc.Views.Block;
import com.tnecesoc.digitblock.DailySignInActivity;
import com.tnecesoc.digitblock.MainActivity;

import java.util.HashMap;

/**
 * Created by Tnecesoc on 2016/6/3.
 */
public class TodaysBlockAnimationControl {

    private DailySignInActivity activity;

    public TodaysBlockAnimationControl(DailySignInActivity activity) {
        this.activity = activity;
    }

    public HashMap<FrameLayout, Block> belongings = new HashMap<FrameLayout, Block>();

    public void generateNewBlockInPosWithValue(FrameLayout position, int value) {

        Block theBlock = new Block(activity);

        Block prevBlock = belongings.get(position);

        if (prevBlock != null) {
            belongings.put(position, null);
            position.removeView(prevBlock);
        }


        theBlock.setValue(value);

        ScaleAnimation animation = new ScaleAnimation(
                0.0f,
                1.0f,
                0.0f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        );
        animation.setDuration(200);
        theBlock.setAnimation(animation);

        position.addView(theBlock);
        belongings.put(position, theBlock);

        animation.startNow();
    }

}
