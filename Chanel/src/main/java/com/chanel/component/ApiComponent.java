package com.chanel.component;

import com.squareup.okhttp.OkHttpClient;

import retrofit.Endpoint;
import retrofit.RestAdapter;
import retrofit.converter.Converter;

/**
 * Used for http client initialization.
 * Also provides OkHttpClient, RestAdapter, Converter, etc.
 * Created by Mayboroda on 5/23/15.
 */
public interface ApiComponent {
    /* Used for sub-graphs. */
    RestAdapter restAdapter();
    OkHttpClient okHttpClient();
    Converter converter();
    Endpoint endpoint();
}
