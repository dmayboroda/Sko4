package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * img entity java representation.
 * Created by Mayboroda on 6/7/16.
 */
public class Image {

    @SerializedName("original")
    private String original;
    @SerializedName("square")
    private String square;

    public String getOriginal() { return original; }

    public String getSquare() { return square; }
}
