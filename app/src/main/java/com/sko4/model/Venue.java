package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * Venue model.
 * Created by Mayboroda.
 */
public class Venue {

    @SerializedName("id")
    private String id;
    @SerializedName("id_table")
    private String tableId;
    @SerializedName("id_item")
    private String itemId;
    @SerializedName("id_venue")
    private String venueId;
    @SerializedName("idt")
    private String idt;
    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String country;
    @SerializedName("idt_city")
    private String idtCity;
    @SerializedName("idt_country")
    private String idtCountry;
    @SerializedName("country_code")
    private String countryCode;
    @SerializedName("name")
    private String name;
    @SerializedName("addr")
    private String address;
    @SerializedName("obj_path")
    private String path;
    @SerializedName("obj_file_name")
    private String fileName;
    @SerializedName("obj_id")
    private String objId;

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

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getIdt() {
        return idt;
    }

    public void setIdt(String idt) {
        this.idt = idt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }
}
