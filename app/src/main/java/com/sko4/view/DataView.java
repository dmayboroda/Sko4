package com.sko4.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sko4.DetailsActivity;
import com.sko4.R;
import com.sko4.Utils;
import com.sko4.api.ApiService;
import com.sko4.model.DataObject;
import com.sko4.model.Details;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Observable;

/**
 * Artist and venue details view.
 * Created by Mayboroda on 6/15/16.
 */
public class DataView extends RxCoordinator<DataObject, DetailsActivity>
                    implements RevealView.OnRevealChange{

    @Inject ApiService apiService;

    @Bind(R.id.map_card)    MapCard mapCard;
    @Bind(R.id.desc_card)   DescCard descCard;
    @Bind(R.id.data_pic)    ImageView picture;
    @Bind(R.id.data_name)   TextView name;
    @Bind(R.id.data_about)  TextView about;
    @Bind(R.id.data_url)    TextView url;

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
    protected void onFinishInflate() {
        super.onFinishInflate();
        name.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_REGULAR));
        about.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
        url.setTypeface(Utils.typeface(getContext(), Utils.ROBOTO_LIGHT));
        String tit  = getActivity().getName();
        String sum  = getActivity().getSum();
        name.setText(tit);
        if (!TextUtils.isEmpty(sum)) {
            about.setText(sum);
        } else {
            about.setVisibility(GONE);
        }
    }

    @Override
    public Observable<DataObject> createObservable(String value) {
        return getActivity().isArtist()
                ? apiService.getArtistData(value)
                : apiService.getVenueData(value);
    }

    @Override
    public void call(DataObject dataObject) {
        if (dataObject == null) {
            switcher.setDisplayedChildId(R.id.error_message);
            return;
        }
        Details details = dataObject.getData();
        mapCard.bind(details.getMapInfo());
        descCard.bind(details.getBody());
        String square = details.getSquareUrl();
        String web    = details.getUrl();
        if (!TextUtils.isEmpty(square)) {
            Glide.with(getContext())
                    .load(square)
                    .transform(new CircleTransform(getContext()))
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(picture);
        } else {
            picture.setVisibility(INVISIBLE);
        }
        if (!TextUtils.isEmpty(web)) {
            url.setText(web);
        } else {
            url.setVisibility(GONE);
        }
        switcher.setDisplayedChildId(R.id.data_view);
    }

    @Override
    public void onRevealChange(int state) {
        if (state == RevealView.FINISHED) {
            switcher.setVisibility(VISIBLE);
        } else {
            switcher.setVisibility(INVISIBLE);
        }
    }
}
