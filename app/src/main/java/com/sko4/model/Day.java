package com.sko4.model;

import com.google.gson.annotations.SerializedName;

/**
 * Day model.
 * Created by Mayboroda.
 */
public class Day {

    @SerializedName("day")
    private String day;
    @SerializedName("month")
    private String month;
    @SerializedName("foramted_date")
    private String foramtedDate;
    @SerializedName("foramted_date_time")
    private String foramtedTimee;
    @SerializedName("time")
    private String time;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("smart")
    private String smart;
    @SerializedName("timezone")
    private String timezone;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getSmart() {
        return smart;
    }

    public void setSmart(String smart) {
        this.smart = smart;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getForamtedTimee() {
        return foramtedTimee;
    }

    public void setForamtedTimee(String foramtedTimee) {
        this.foramtedTimee = foramtedTimee;
    }

    public String getForamtedDate() {
        return foramtedDate;
    }

    public void setForamtedDate(String foramtedDate) {
        this.foramtedDate = foramtedDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
