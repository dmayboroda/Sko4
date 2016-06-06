package com.sko4.model;

import org.joda.time.DateTime;

/**
 * Bindable interface for view.
 * Created by Mayboroda.
 */
public interface Bindable {

    String getName();
    String getStyles();
    String getUrl();
    String getId();
    String getVendor();
    String getPrice();
    DateTime getStartDate();
    DateTime getFinishDate();
}
