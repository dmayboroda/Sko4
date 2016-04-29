package com.sko4.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Basic parser model.
 * Created by Mayboroda.
 */
public class EventsWrapper {

    @SerializedName("success")
    private boolean success;
    @SerializedName("limit")
    private int limit;
    @SerializedName("count_loaded")
    private int countLoaded;
    @SerializedName("list")
    private List<Event> events;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCountLoaded() {
        return countLoaded;
    }

    public void setCountLoaded(int countLoaded) {
        this.countLoaded = countLoaded;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
