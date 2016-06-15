package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * Photo entity.
 * Created by Mayboroda on 6/15/16.
 */
public class Photo {

    @SerializedName("id")
    private String id;
    @SerializedName("path")
    private String path;
    @SerializedName("file_name")
    private String name;

    public String getOriginal() {
        return "http://files.sko4.com"
                .concat(path)
                .concat(id)
                .concat("original")
                .concat(name);
    }

    public String getSquare() {
        return "http://files.sko4.com"
                .concat(path)
                .concat(id)
                .concat("square")
                .concat(name);
    }

}
