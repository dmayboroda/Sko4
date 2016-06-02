package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * Stage model overview.
 * Created by Mayboroda on 5/31/16.
 */
public class Stage {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getId() { return id; }

    public String getName() { return name; }
}
