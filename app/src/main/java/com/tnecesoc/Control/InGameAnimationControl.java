package com.tnecesoc.Control;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
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

    private HashMap<FrameLayout, Block> blockList = new HashMap<FrameLayout, Block>();

    private void internallyDeleteBlockIn(int x, int y) {

        FrameLayout position = activity.theCellLocatedIn[x][y];

        Block target = blockList.get(position);

        blockList.put(position, null);

        position.removeView(target);
        
    }

    private void internallyAddBlockIn(int x, int y, Block target) {

        FrameLayout position = activity.theCellLocatedIn[x][y];

        blockList.put(position, target);

        position.addView(target);

    }

    void attachNewBlockIn(int x, int y) {

        Block newBlock = new Block(activity);

        newBlock.setValue(2);

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
        newBlock.setAnimation(animation);

        internallyAddBlockIn(x, y, newBlock);

        animation.startNow();
    }

    void mergeBlock(
            int conquerorX,
            int conquerorY,
            int conquerorValue,
            int targetX,
            int targetY,
            int targetValue
    ) {

        if (conquerorX == targetX && conquerorY == targetY) {
            return;
        }

        Block conqueror = blockList.get(activity.theCellLocatedIn[conquerorX][conquerorY]);
        Block target = blockList.get(activity.theCellLocatedIn[targetX][targetY]);

        if (target == null) {
            return;
        }

        int horizontalDistance = targetX - conquerorX;
        int verticalDistance = targetY - conquerorY;

        TranslateAnimation leaveAnimation = new TranslateAnimation(0, -1000 * horizontalDistance, 0, 1000 * verticalDistance);
        TranslateAnimation approachAnimation = new TranslateAnimation(1000 * horizontalDistance, 0, -1000 * verticalDistance, 0);
        approachAnimation.setDuration(300);
        leaveAnimation.setDuration(300);

        target.setAnimation(leaveAnimation);

        if (conqueror != null) {
            conqueror.setAnimation(leaveAnimation);
        }

        leaveAnimation.startNow();

        Block finalState = new Block(activity);
        finalState.setValue(conquerorValue + targetValue);

        internallyDeleteBlockIn(conquerorX, conquerorY);

        finalState.setAnimation(approachAnimation);

        internallyAddBlockIn(conquerorX, conquerorY, finalState);

        approachAnimation.startNow();

        internallyDeleteBlockIn(targetX, targetY);

    }


    void resetCellIn(int x, int y) {

        FrameLayout position = activity.theCellLocatedIn[x][y];

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

        Block target = blockList.get(position);

        if (target == null) {
            return;
        }

        target.setAnimation(animation);

        animation.startNow();

        position.removeView(target);
        blockList.put(position, null);
    }

}
