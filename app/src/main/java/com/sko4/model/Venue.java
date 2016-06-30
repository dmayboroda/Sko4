package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * Venue model.
 * Created by Mayboroda.
 */
public class Venue {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("addr")
    private String address;
    @SerializedName("city")
    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
