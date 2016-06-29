package com.sko4.view;

import android.content.Context;
import android.util.AttributeSet;
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


    @Inject ApiService apiService;

    public DataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            component.inject(this);
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String dataId = getActivity().getDataId();
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
}
