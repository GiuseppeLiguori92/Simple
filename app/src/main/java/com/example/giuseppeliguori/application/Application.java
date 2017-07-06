package com.example.giuseppeliguori.application;

import com.facebook.stetho.Stetho;

/**
 * Created by giuseppeliguori on 06/07/2017.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
