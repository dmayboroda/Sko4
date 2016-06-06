package com.sko4.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Event api data overview.
 * Created by Mayboroda on 6/4/16.
 */
public class EventData {

    @SerializedName("data")
    private Event data;
    @SerializedName("stages")
    private List<Stage> stages;

    public Event getData() { return data; }

    public void setData(Event data) {
        this.data = data;
    }

    public List<Stage> getStages() { return stages; }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }
}
