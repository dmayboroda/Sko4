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
public class Event implements Bindable {

    @SerializedName("id")
    private String id;
    @SerializedName("idt")
    private String idt;
    @SerializedName("path")
    private String path;
    @SerializedName("file_name")
    private String file;
    @SerializedName("title")
    private String title;
    @SerializedName("city")
    private String city;
    @SerializedName("idt_city")
    private String idtCity;
    @SerializedName("idt_country")
    private String idtCountry;
    @SerializedName("prices")
    private List<Price> prices;
    @SerializedName("styles")
    private List<Style> styles;
    @SerializedName("venues")
    private List<Venue> venues;
    @SerializedName("start_date")
    private String date;

    @Nullable
    public DateTime getDate() {
        DateTime dateTime = null;
        if (!TextUtils.isEmpty(date)) {
            dateTime = DateTime.parse(date);
        }
        return dateTime;
    }

    public void setDate(String date) { this.date = date; }

    @Override
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

    @Override
    public String getUrl() {
        return "http://files.sko4.com"
                + getPath()
                + getId()
                + "original"
                + getFile();
    }

    @Override
    public String getId() { return id; }

    @Override
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

    @Override
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

    private void setupPrice(StringBuilder builder, Price price){
        if (price.getPrice().equals("0.00")) {
            builder.append("Free");
        } else {
            builder.append(price.getPrice());
        }
        builder.append(" ");
        builder.append(price.getSymbol());
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdt() {
        return idt;
    }

    public void setIdt(String idt) {
        this.idt = idt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIdtCity() {
        return idtCity;
    }

    public void setIdtCity(String idtCity) {
        this.idtCity = idtCity;
    }

    public String getIdtCountry() {
        return idtCountry;
    }

    public void setIdtCountry(String idtCountry) {
        this.idtCountry = idtCountry;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<Style> getStylesList() {
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
}
