package com.sko4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Reveal circle animation view.
 * Created by Mayboroda on 6/30/16.
 */
public class RevealView extends View {

    public static final int NOT_STARTED = 0;
    public static final int STARTED     = 1;
    public static final int FINISHED    = 2;

    private static final Interpolator INTERPOLATOR  = new AccelerateInterpolator();
    private static final int DURATION               = 400;

    public interface OnRevealChange {
        void onRevealChange(int state);
    }
    private int state = NOT_STARTED;

    private Paint paint;
    private int radius;
    private ObjectAnimator reveal;
    private int[] coords;

    private OnRevealChange onRevealChange;

    public RevealView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
    }

    public void setOnRevealChange(OnRevealChange onRevealChange) {
        this.onRevealChange = onRevealChange;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public void finish() {
        changeState(FINISHED);
        invalidate();
    }

    public void start(int[] coords) {
        changeState(STARTED);
        this.coords = coords;
        int start = getWidth() + getHeight();
        reveal = ObjectAnimator.ofInt(this, "radius", 0, start);
        reveal.setDuration(DURATION);
        reveal.setInterpolator(INTERPOLATOR);
        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                changeState(FINISHED);
            }
        });
        reveal.start();
    }

    private void changeState(int state){
        if (this.state == state) { return; }
        this.state = state;
        if (onRevealChange != null) {
            onRevealChange.onRevealChange(state);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (state == FINISHED) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        } else {
            canvas.drawCircle(coords[0], coords[1], radius, paint);
        }
    }
}
