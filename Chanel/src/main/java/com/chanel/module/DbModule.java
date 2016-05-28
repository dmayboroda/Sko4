package com.chanel.module;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chanel.BuildConfig;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Database module
 * <p/>
 * Created by a-petruk on 23.06.15.
 */
@Module
public final class DbModule {
    private static final String LOG_TAG = DbModule.class.getSimpleName();

    private final SQLiteOpenHelper openHelper;

    public DbModule(SQLiteOpenHelper openHelper) {
        this.openHelper = openHelper;
    }

    @Provides @Singleton
    public SQLiteOpenHelper provideOpenHelper() {
        return openHelper;
    }

    @Provides @Singleton
    SqlBrite provideSqlBrite(SQLiteOpenHelper openHelper) {
        SqlBrite db = SqlBrite.create(openHelper);
        if (BuildConfig.DEBUG) {
            db.setLogger(new SqlBrite.Logger() {
                @Override
                public void log(String message) {
                    Log.d(LOG_TAG, message);
                }
            });
            db.setLoggingEnabled(true);
        }
        return db;
    }
}
