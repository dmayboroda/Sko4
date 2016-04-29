package com.sko4.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Filters object class.
 * Created by Mayboroda.
 */
public class Filters {

    @SerializedName("styles")
    private List<Style> styles;
    @SerializedName("venues")
    private List<Venue> venues;
    @SerializedName("types")
    private List<EventType> types;

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public List<EventType> getTypes() {
        return types;
    }

    public void setTypes(List<EventType> types) {
        this.types = types;
    }
}
