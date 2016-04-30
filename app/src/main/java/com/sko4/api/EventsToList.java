package com.sko4.api;

import com.sko4.model.Bindable;
import com.sko4.model.Event;
import com.sko4.model.EventsWrapper;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

/**
 * Function for converting wrapper to list.
 * Created by Mayboroda.
 */
public final class EventsToList implements Func1<EventsWrapper, List<Bindable>> {

    private static volatile EventsToList instance;

    public static EventsToList instance() {
        if (instance == null) {
            instance = new EventsToList();
        }
        return instance;
    }

    @Override
    public List<Bindable> call(EventsWrapper eventsWrapper) {
        return new ArrayList<Bindable>(eventsWrapper.getEvents());
    }
}
