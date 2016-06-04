package com.tnecesoc.Control;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.tnecesoc.Views.Block;
import com.tnecesoc.digitblock.MainActivity;

import java.util.HashMap;

/**
 * Created by Tnecesoc on 2016/6/1.
 */
public class InGameAnimationControl {

    private MainActivity activity;

    public InGameAnimationControl(MainActivity activity) {
        this.activity = activity;
    }

    public HashMap<FrameLayout, Block> belongings = new HashMap<FrameLayout, Block>();

    public void generateNewBlockIn(FrameLayout position) {

        Block theBlock = new Block(activity);
        theBlock.setValue(2);

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

    public void annexBlock(
                                  int annexerX,
                                  int annexerY,
                                  int annexerValue,
                                  int targetX,
                                  int targetY,
                                  int targetValue
    ) {

        if ((annexerX == targetX) && (annexerY == targetY)) {
            return;
        }

        FrameLayout annexer_pos = activity.screen_pos.get(activity.screen[annexerX][annexerY]);
        FrameLayout target_pos = activity.screen_pos.get(activity.screen[targetX][targetY]);

        Block annexer = belongings.get(annexer_pos);
        Block target = belongings.get(target_pos);

        if (target == null) {
            return;
        }

        int dir_horizonal = targetX - annexerX;
        int dir_vertical = targetY - annexerY;

        TranslateAnimation initialAnimation = new TranslateAnimation(0, -1000 * dir_horizonal, 0, 1000 * dir_vertical);
        TranslateAnimation finalAnimation = new TranslateAnimation(1000*dir_horizonal, 0, -1000 * dir_vertical, 0);
        finalAnimation.setDuration(300);
        initialAnimation.setDuration(300);

        target.setAnimation(initialAnimation);
        if (annexer != null) {
            annexer.setAnimation(initialAnimation);
        }
        initialAnimation.startNow();

        Block finalState = new Block(activity);
        finalState.setValue(annexerValue + targetValue);
        annexer_pos.addView(finalState);

        finalState.setAnimation(finalAnimation);
        finalAnimation.startNow();

        belongings.put(target_pos, null);
        belongings.put(annexer_pos, finalState);
        target_pos.removeView(target);
        annexer_pos.removeView(annexer);
    }


    public void removeBlockAt(FrameLayout position) {

        ScaleAnimation animation = new ScaleAnimation(
                1.0f,
                0.0f,
                1.0f,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        );
        animation.setDuration(200);

        Block target = belongings.get(position);

        if (target == null) {
            return;
        }

        target.setAnimation(animation);

        animation.startNow();

        position.removeView(target);
        belongings.put(position, null);
    }

}
