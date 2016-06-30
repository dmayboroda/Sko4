package com.sko4.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

/**
 * Details object for artist and event.
 * Created by Mayboroda on 6/21/16.
 */
public class Details {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("path")
    private String path;
    @SerializedName("file_name")
    private String fileName;
    @SerializedName("img")
    private Image image;
    @SerializedName("styles")
    private List<Style> styles;
    @SerializedName("media")
    private List<Media> media;
    @SerializedName("addr")
    private String address;
    @SerializedName("maps")
    private List<MapInfo> mapInfo;
    @SerializedName("url")
    private String url;
    @SerializedName("desc_ru")
    private String body;
    private String sum;

    public Details(Venue venue) {
        this.name = venue.getName();
        this.id = venue.getId();
        this.sum = venue.getAddress();
    }

    public Details() { /* nothing */ }

    public String getSum() {
        if (TextUtils.isEmpty(sum)) {
            StringBuilder builder = new StringBuilder();
            if (styles != null && !styles.isEmpty()) {
                for (Style style : styles) {
                    if (builder.length() > 0) {
                        builder.append(", ");
                    }
                    builder.append(style.getName());
                }
                return builder.toString();
            }
        }
        return sum;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Image getImage() { return image; }

    public List<Style> getStyles() {
        if (styles == null) {
            return Collections.emptyList();
        }
        return styles;
    }

    @Nullable
    public String getSquareUrl() {
        if (image == null) { return null; }
        String square = image.getSquare();
        return !TextUtils.isEmpty(square) ?
                "http://files.sko4.com" + square : null;
    }

    public String getImageByPath() {
        StringBuilder builder = new StringBuilder();
        builder.append("http://files.sko4.com");
        builder.append(path);
        builder.append(id);
        builder.append("square");
        builder.append(fileName);
        return builder.toString();
    }

    public String getBody() { return body; }

    public List<Media> getMedia() { return media; }

    public String getAddress() { return address; }

    public List<MapInfo> getMapInfo() { return mapInfo; }

    public String getUrl() { return url; }
}
