package com.sko4.api;

import com.sko4.model.DataObject;
import com.sko4.model.EventData;
import com.sko4.model.EventsWrapper;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Api service.
 * Created by Mayboroda.
 */
public interface ApiService {

    @GET("/api")
    Observable<EventsWrapper> getEvents(@Query("id_city") String city);

    @GET("/api/{id}")
    Observable<EventData> getEventData(@Path("id") String id);

    @GET("/api/artist/{id}")
    Observable<DataObject> getArtistData(@Path("id") String id);

    @GET("/api/venue/{id}")
    Observable<DataObject> getVenueData(@Path("id") String id);
}
