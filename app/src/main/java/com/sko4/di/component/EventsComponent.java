package com.sko4.di.component;

import com.sko4.Cycles;
import com.sko4.Lifecycle;
import com.sko4.di.module.EventsModule;
import com.sko4.view.DataView;
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
    void inject(DataView view);
}
