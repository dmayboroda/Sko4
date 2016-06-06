package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * Map object parsing.
 * Created by Mayboroda on 6/6/16.
 */
public class MapInfo {

    @SerializedName("id")
    private String id;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("address")
    private String address;

    public String getId() { return id; }

    public String getLongitude() { return longitude; }

    public String getLatitude() { return latitude; }

    public String getAddress() { return address; }
}
