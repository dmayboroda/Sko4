package com.sko4.model;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("desc")
    private String desc;
    @SerializedName("id_sc_track")
    private String soundcloud;
    @SerializedName("id_type")
    private String type;
    @SerializedName("id_status")
    private String status;
    @SerializedName("city")
    private String city;
    @SerializedName("idt_city")
    private String idtCity;
    @SerializedName("idt_country")
    private String idtCountry;
    @SerializedName("country_code")
    private String countryCode;
    @SerializedName("is_finished")
    private int isFinished;
    @SerializedName("days_to")
    private int daysTo;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("finish_date")
    private String finishtDate;
    @SerializedName("prices")
    private List<Price> prices;
    @SerializedName("artists")
    private List<Artist> artists;
    @SerializedName("styles")
    private List<Style> styles;
    @SerializedName("venues")
    private List<Venue> venues;

    @Override
    public String getName() { return title; }

    @Override
    public String getPlus() {
        List<Style> types = getStyles();
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
    public String getEndDate() {
        return finishtDate;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

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
            for (Price price : prices) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                if (price.getPrice().equals("0.00")) {
                    builder.append("Free");
                } else {
                    builder.append(price.getPrice());
                }
                builder.append(price.getSymbol());
            }
        }
        return builder.toString();
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSoundcloud() {
        return soundcloud;
    }

    public void setSoundcloud(String soundcloud) {
        this.soundcloud = soundcloud;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public int getDaysTo() {
        return daysTo;
    }

    public void setDaysTo(int daysTo) {
        this.daysTo = daysTo;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

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
}
