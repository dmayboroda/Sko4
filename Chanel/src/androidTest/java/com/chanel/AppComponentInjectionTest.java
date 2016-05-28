package com.chanel;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.chanel.component.DaggerTestAppComponent;
import com.chanel.component.TestAppComponent;
import com.chanel.module.ApiModule;
import com.chanel.module.AppModule;
import com.squareup.okhttp.OkHttpClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit.Endpoint;
import retrofit.RestAdapter;
import retrofit.converter.Converter;

/**
 * App component test
 * Created by a-petruk on 24.06.15.
 */
@RunWith(AndroidJUnit4.class)
public class AppComponentInjectionTest extends InstrumentationTestCase {
    public static final String TEST_URL = "http://localhost:8888";

    private TestAppComponent appComponent;

    @Inject Context context;
    @Inject SharedPreferences sharedPreferences;
    @Inject Executor executor;
    @Inject RestAdapter restAdapter;
    @Inject Endpoint endpoint;
    @Inject Converter converter;
    @Inject OkHttpClient okHttpClient;

    @Before @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        Context context = getInstrumentation().getContext();
        appComponent = DaggerTestAppComponent.builder()
                .appModule(new AppModule(context))
                .apiModule(new ApiModule(TEST_URL))
                .build();
        appComponent.inject(this);
    }

    @Test
    public void testIAppModuleInjection() {
        assertNotNull(context);
        assertNotNull(sharedPreferences);
        assertNotNull(executor);
    }

    @Test
    public void testIApiModuleInjection() {
        assertNotNull(restAdapter);
        assertNotNull(endpoint);
        assertNotNull(converter);
        assertNotNull(okHttpClient);
    }


    @After @Override
    public void tearDown() throws Exception {
        super.tearDown();
        appComponent = null;
    }

    private static class MockSqlOpenHelper extends SQLiteOpenHelper {

        public MockSqlOpenHelper(Context context) {
            super(context, "", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            throw new UnsupportedOperationException();
        }
    }
}
