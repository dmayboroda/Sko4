package com.sko4.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sko4.R;
import com.sko4.model.MapInfo;

import java.util.List;

/**
 * Event map card view.
 * Created by Mayboroda on 6/6/16.
 */
public class MapCard extends CardView implements OnMapReadyCallback {

    private static final int ZOOM_LEVEL = 15;

    private GoogleMap map;

    public MapCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        SupportMapFragment mapFragment = (SupportMapFragment)
                ((AppCompatActivity)getContext())
                .getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
    }

    public void bind(List<MapInfo> mapInfo) {
        if (mapInfo!= null && !mapInfo.isEmpty()) {
            MapInfo info = mapInfo.get(0);
            double lat = Double.valueOf(info.getLatitude());
            double lon = Double.valueOf(info.getLongitude());
            LatLng location = new LatLng(lat, lon);
            String address = info.getAddress();
            map.addMarker(new MarkerOptions().position(location)
                    .title(address)).showInfoWindow();
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, ZOOM_LEVEL));
            setVisibility(VISIBLE);
        } else {
            setVisibility(GONE);
        }
    }
}
