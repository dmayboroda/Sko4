package com.sko4;

import android.support.v7.app.AppCompatActivity;

import com.sko4.di.component.AppComponent;


/**
 * Base activity with app component.
 * Created by Mayboroda.
 */
public class BaseActivity extends AppCompatActivity {

    public AppComponent getAppComponent() {
        return ((Sko4)getApplication()).appComponent();
    }

}
