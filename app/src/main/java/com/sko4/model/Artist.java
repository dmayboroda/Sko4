package com.sko4.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Artist model.
 * Created by Mayboroda.
 */
public class Artist {

    @SerializedName("is_headliner")
    private String isHeadliner;
    @SerializedName("is_show_card")
    private String isShowCardr;
    @SerializedName("id_stage")
    private String idStage;
    @SerializedName("id")
    private String id;
    @SerializedName("idt")
    private String idt;
    @SerializedName("name")
    private String name;
    @SerializedName("path")
    private String path;
    @SerializedName("file_name")
    private String fileName;
    @SerializedName("id_sc_track")
    private String soundcloud;
    @SerializedName("code")
    private String code;
    @SerializedName("styles")
    private List<Style> styles;

    public String getIsHeadliner() {
        return isHeadliner;
    }

    public void setIsHeadliner(String isHeadliner) {
        this.isHeadliner = isHeadliner;
    }

    public String getIsShowCardr() {
        return isShowCardr;
    }

    public void setIsShowCardr(String isShowCardr) {
        this.isShowCardr = isShowCardr;
    }

    public String getIdStage() {
        return idStage;
    }

    public void setIdStage(String idStage) {
        this.idStage = idStage;
    }

    public String getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSoundcloud() {
        return soundcloud;
    }

    public void setSoundcloud(String soundcloud) {
        this.soundcloud = soundcloud;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }
}
