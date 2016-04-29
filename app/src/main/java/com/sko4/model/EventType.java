package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * Event type.
 * Created by Mayboroda.
 */
public class EventType {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("count_events")
    private String count;

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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
