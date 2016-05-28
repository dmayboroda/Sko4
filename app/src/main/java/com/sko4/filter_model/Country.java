
package com.sko4.filter_model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Country {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idt")
    @Expose
    private String idt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("count_events")
    @Expose
    private String countEvents;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("image")
    @Expose
    private String image;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The idt
     */
    public String getIdt() {
        return idt;
    }

    /**
     * 
     * @param idt
     *     The idt
     */
    public void setIdt(String idt) {
        this.idt = idt;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The countEvents
     */
    public String getCountEvents() {
        return countEvents;
    }

    /**
     * 
     * @param countEvents
     *     The count_events
     */
    public void setCountEvents(String countEvents) {
        this.countEvents = countEvents;
    }

    /**
     * 
     * @return
     *     The countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 
     * @param countryCode
     *     The country_code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

}
