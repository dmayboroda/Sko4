
package com.sko4.filter_model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.validation.Valid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Filters {

    @SerializedName("styles")
    @Expose
    @Valid
    private List<Style> styles = new ArrayList<Style>();
    @SerializedName("venues")
    @Expose
    @Valid
    private List<Venue> venues = new ArrayList<Venue>();
    @SerializedName("types")
    @Expose
    @Valid
    private List<Type> types = new ArrayList<Type>();
    @SerializedName("cities")
    @Expose
    @Valid
    private List<City> cities = new ArrayList<City>();
    @SerializedName("countries")
    @Expose
    @Valid
    private List<Country> countries = new ArrayList<Country>();

    /**
     * 
     * @return
     *     The styles
     */
    public List<Style> getStyles() {
        return styles;
    }

    /**
     * 
     * @param styles
     *     The styles
     */
    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    /**
     * 
     * @return
     *     The venues
     */
    public List<Venue> getVenues() {
        return venues;
    }

    /**
     * 
     * @param venues
     *     The venues
     */
    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<Type> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    /**
     * 
     * @return
     *     The cities
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * 
     * @param cities
     *     The cities
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * 
     * @return
     *     The countries
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * 
     * @param countries
     *     The countries
     */
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}
