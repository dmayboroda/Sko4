package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * Style model.
 * Created by Mayboroda.
 */
public class Style {

    @SerializedName("id")
    private String id;
    @SerializedName("id_table")
    private String tableId;
    @SerializedName("id_item")
    private String itemId;
    @SerializedName("id_music_style")
    private String isMusicStyle;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getIsMusicStyle() {
        return isMusicStyle;
    }

    public void setIsMusicStyle(String isMusicStyle) {
        this.isMusicStyle = isMusicStyle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
