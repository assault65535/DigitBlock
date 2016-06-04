package com.tnecesoc.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.tnecesoc.Model.Consts.MyColor;
import com.tnecesoc.digitblock.R;

/**
 * TODO: document your custom view class.
 */
public class Block extends View {

    private int width = 200;
    private int height = 200;
    private int color = MyColor.BLOCK_COLOR[0];
    private int value = 0;

    public Block(Context context) {
        super(context);
        init(null, 0);
    }

    public Block(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Block(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void setValue(int value) {
        this.value = value;
        setColor(MyColor.findColorByInt(value));
    }

    public void setColor(int color) {
        this.color = color;
        postInvalidate();
    }

    public int getValue() {
        return value;
    }

    public void setValueT(int value) {
        this.value = value;
        this.color = MyColor.findColorByInt(value);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Block, defStyle, 0);

        color = a.getColor(
                R.styleable.Block_Color,
                color);

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        @SuppressLint("DrawAllocation")
        Rect rect = new Rect(0, 0, width, height);
        @SuppressLint("DrawAllocation")
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);

        if (value > 0) {
            paint.setColor((value >= 8 ? Color.WHITE : Color.DKGRAY));
            paint.setFakeBoldText(true);
            paint.setTextSize(64);
            paint.setAntiAlias(true);
            paint.setTextAlign(Paint.Align.CENTER);

            Paint.FontMetrics fm = paint.getFontMetrics();
            float textHeight = fm.bottom - fm.top;
            float textBaseY = getMeasuredHeight() - (getMeasuredHeight() - textHeight) / 2 - fm.bottom;
            float textBaseX = getMeasuredWidth() / 2;

            canvas.drawText(Integer.toString(value), textBaseX, textBaseY, paint);
        }

        canvas.save();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }
}
