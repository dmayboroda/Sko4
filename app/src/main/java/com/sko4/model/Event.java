package com.sko4.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Event model.
 * Created by Mayboroda.
 */
public class Event {

    @SerializedName("id")
    private String id;
    @SerializedName("path")
    private String path;
    @SerializedName("file_name")
    private String file;
    @SerializedName("title")
    private String title;
    @SerializedName("city")
    private String city;
    @SerializedName("prices")
    private List<Price> prices;
    @SerializedName("styles")
    private List<Style> styles;
    @SerializedName("venues")
    private List<Venue> venues;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("finish_date")
    private String finishDate;
    @SerializedName("url")
    private String url;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("url_buy_tickets")
    private String tickets;
    @SerializedName("maps")
    private List<MapInfo> mapInfo;
    @SerializedName("body")
    private String body;
    @SerializedName("artists")
    private List<Details> artists;

    @Nullable
    public DateTime getStartDate() {
        DateTime dateTime = null;
        if (!TextUtils.isEmpty(startDate)) {
            dateTime = DateTime.parse(startDate);
        }
        return dateTime;
    }

    public DateTime getFinishDate() {
        DateTime dateTime = null;
        if (!TextUtils.isEmpty(finishDate)) {
            dateTime = DateTime.parse(finishDate);
        }
        return dateTime;
    }

    public String getName() { return title; }

    public String getStyles() {
        List<Style> types = getStylesList();
        if (types == null || types.isEmpty()) { return ""; }
        StringBuilder builder = new StringBuilder();
        for (Style style : types) {
            if (builder.length() > 0) {
                builder.append(" | ");
            }
            builder.append(style.getName());
        }
        return builder.toString();
    }

    public String getUrl() {
        return "http://files.sko4.com"
                + getPath()
                + getId()
                + "original"
                + getFile();
    }

    public String getId() { return id; }

    public String getVenueId() {
        return venues.get(0).getId();
    }

    public String getVendor() {
        StringBuilder builder = new StringBuilder();
        if (venues != null && !venues.isEmpty()) {
            for (Venue venue : venues) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append(venue.getName());
            }
        }
        return builder.toString();
    }

    public String getPrice() {
        StringBuilder builder = new StringBuilder();
        if (prices != null && !prices.isEmpty()) {
            if (prices.size() > 1) {
                Price first = prices.get(0);
                setupPrice(builder, first);
                builder.append(" - ");
                int lastIdx = prices.size() - 1;
                Price last = prices.get(lastIdx);
                setupPrice(builder, last);
            } else {
                setupPrice(builder, prices.get(0));
            }
        }
        return builder.toString();
    }

    public String getFacebook() { return facebook; }

    public String getTickets() { return tickets; }

    public String getWeb() { return url; }

    private void setupPrice(StringBuilder builder, Price price){
        if (price.getPrice().equals("0.00")) {
            builder.append("Free");
        } else {
            builder.append(price.getPrice());
        }
        builder.append(" ");
        builder.append(price.getSymbol());
    }

    public boolean isInfoDisable() {
        return TextUtils.isEmpty(facebook)
                && TextUtils.isEmpty(url)
                && TextUtils.isEmpty(getPrice())
                && TextUtils.isEmpty(getVenue())
                && startDate == null
                && finishDate == null;
    }

    public boolean isToday() {
        DateTime date = getStartDate();
        return date != null && date.isEqualNow();
    }

    public List<Venue> getHosts() {
        return venues;
    }

    @Nullable
    public String getVenue() {
        if (venues == null || venues.isEmpty()) { return null; }
        StringBuilder builder = new StringBuilder();
        for (Venue venue : venues) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            if (!TextUtils.isEmpty(venue.getName())) {
                builder.append(venue.getName());
            }
        }
        return builder.toString();
    }

    public String getCity() { return city; }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Style> getStylesList() {
        return styles;
    }

    public List<MapInfo> getMapInfo() { return mapInfo; }

    public String getBody() { return body; }

    public List<Details> getArtists() { return artists; }
}
