package com.sko4.view;

import android.content.Context;
import android.util.AttributeSet;

import com.sko4.DetailsActivity;
import com.sko4.api.ApiService;
import com.sko4.model.ArtistData;

import javax.inject.Inject;

import rx.Observable;

/**
 * Artist view.
 * Created by Mayboroda on 6/15/16.
 */
public class ArtistView extends RxCoordinator<ArtistData, DetailsActivity>{

    @Inject ApiService apiService;

    public ArtistView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            component.inject(this);
        }
    }

    @Override
    public Observable<ArtistData> createObservable(String value) {
        return apiService.getArtistData(value);
    }

    @Override
    public void call(ArtistData artistData) {

    }
}
