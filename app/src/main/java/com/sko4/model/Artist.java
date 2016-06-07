package com.sko4.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

/**
 * Artist model.
 * Created by Mayboroda.
 */
public class Artist {

    @SerializedName("id_stage")
    private String idStage;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("path")
    private String path;
    @SerializedName("file_name")
    private String fileName;
    @SerializedName("id_sc_track")
    private String soundcloud;
    @SerializedName("img")
    private Image image;
    @SerializedName("styles")
    private List<Style> styles;

    public String getIdStage() {
        return idStage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() { return path; }

    public String getFileName() {
        return fileName;
    }

    public String getSoundcloud() {
        return soundcloud;
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

}
