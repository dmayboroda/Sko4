package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * Media data representation.
 * Created by Mayboroda on 6/15/16.
 */
public class Media {

    @SerializedName("id")
    private String id;
    @SerializedName("code")
    private String code;

    public String getId() { return id; }

    public String getCode() { return code; }
}
