package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * Price model.
 * Created by Mayboroda.
 */
public class Price {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private String price;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
