package com.sko4.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model for specific artist request.
 * Created by Mayboroda on 6/15/16.
 */
public class ArtistData {

    @SerializedName("data")
    private Artist artist;
    @SerializedName("photos")
    private List<Photo> photos;

    public Artist getArtist() { return artist; }

    public List<Photo> getPhotos() { return photos; }
}
