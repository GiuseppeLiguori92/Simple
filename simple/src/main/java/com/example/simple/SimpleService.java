package com.example.simple;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.apache.commons.lang3.SerializationUtils;

/**
 * Created by giuseppeliguori on 7/5/17.
 */

public class SimpleService extends Service {

    private static final String TAG = "SimpleService";
    public static final String OBJECT = "OBJECT";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SimpleObject simpleObject;
        if (intent.hasExtra(OBJECT)) {
            byte[] object = intent.getByteArrayExtra(OBJECT);
            simpleObject = (SimpleObject) SerializationUtils.deserialize(object);
            simpleObject.run();
        } else {
            System.out.println("SimpleService.onStartCommand: NO_EXTRA");
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
