package com.sko4.di;

import com.chanel.Cycles;
import com.chanel.Lifecycle;
import com.chanel.component.AppComponent;
import com.sko4.view.EventView;
import com.sko4.view.ItemsListView;

import dagger.Component;

/**
 * Api service component.
 * Created by Mayboroda.
 */
@Lifecycle(Cycles.VIEW)
@Component(
        modules = EventsModule.class,
        dependencies = AppComponent.class
)
public interface EventsComponent {
    void inject(ItemsListView view);
    void inject(EventView view);
}
