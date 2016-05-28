
package com.sko4.filter_model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class City {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idt")
    @Expose
    private String idt;
    @SerializedName("idt_country")
    @Expose
    private String idtCountry;
    @SerializedName("id_country")
    @Expose
    private String idCountry;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("count_events")
    @Expose
    private String countEvents;
    @SerializedName("country_code")
    @Expose
    private String countryCode;

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
     *     The idtCountry
     */
    public String getIdtCountry() {
        return idtCountry;
    }

    /**
     * 
     * @param idtCountry
     *     The idt_country
     */
    public void setIdtCountry(String idtCountry) {
        this.idtCountry = idtCountry;
    }

    /**
     * 
     * @return
     *     The idCountry
     */
    public String getIdCountry() {
        return idCountry;
    }

    /**
     * 
     * @param idCountry
     *     The id_country
     */
    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
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

}
