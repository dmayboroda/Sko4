package com.sko4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

import com.sko4.DetailsActivity;
import com.sko4.api.ApiService;
import com.sko4.model.DataObject;

import javax.inject.Inject;

import rx.Observable;

/**
 * Artist and venue details view.
 * Created by Mayboroda on 6/15/16.
 */
public class DataView extends RxCoordinator<DataObject, DetailsActivity>{

    private static final Interpolator INTERPOLATOR  = new AccelerateInterpolator();
    private static final int ANIMATION_TIME         = 400;
    private static final int START                  = 0;
    private static final int FINISHED               = 1;
    private static final int WAITING                = 2;


    private int state = WAITING;
    private Paint paint;
    private int x, y;
    private int radius;

    @Inject ApiService apiService;

    public DataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            component.inject(this);
        }
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String dataId = getActivity().getDataId();
        int[] coords = getActivity().getCoords();
        x = coords[0];
        y = coords[1];
        eventSubject.onNext(dataId);
    }

    @Override
    public Observable<DataObject> createObservable(String value) {
        return getActivity().isArtist()
                ? apiService.getArtistData(value)
                : apiService.getVenueData(value);
    }

    @Override
    public void call(DataObject dataObject) {

    }

    public void startAnimation() {
        state = START;
        int endValue = getWidth() + getHeight();
        ObjectAnimator reveal = ObjectAnimator.ofInt(this, "currentRadius", 0, endValue);
        reveal.setDuration(ANIMATION_TIME);
        reveal.setInterpolator(INTERPOLATOR);
        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                state = FINISHED;
            }
        });
        reveal.start();
    }

    public void finishAnimation() {
        state = FINISHED;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (state == FINISHED) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        } else {
            canvas.drawCircle(x, y, radius, paint);
        }
    }
}
