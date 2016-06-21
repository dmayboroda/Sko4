package com.sko4.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model for specific artist request.
 * Created by Mayboroda on 6/15/16.
 */
public class DataObject {

    @SerializedName("data")
    private Details data;
    @SerializedName("photos")
    private List<Photo> photos;
    @SerializedName("events")
    private List<Event> events;

    public Details getArtist() { return data; }

    public List<Photo> getPhotos() { return photos; }

    public List<Event> getEvents() { return events; }
}