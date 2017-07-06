package com.example.simple;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.simple.database.DatabaseHelperSimpleObject;
import com.example.simple.database.DatabaseSimpleObject;
import com.example.simple.events.screen.ScreenOff;
import com.example.simple.events.screen.ScreenOn;

import java.util.List;

/**
 * Created by giuseppeliguori on 7/5/17.
 */

public class SimpleService extends Service {

    private static final String TAG = "SimpleService";
    public static final String OBJECT = "OBJECT";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        DatabaseSimpleObject databaseSimpleObject = DatabaseSimpleObject.getInstance(this);
        List<SimpleObject> simpleObjects = databaseSimpleObject.getSimpleObjects();

        if (simpleObjects == null) {
            return START_NOT_STICKY;
        }

        for (SimpleObject simpleObject : simpleObjects) {
            processSimpleObject(simpleObject, intent);
        }
        return START_NOT_STICKY;
    }

    private void processSimpleObject(SimpleObject simpleObject, Intent intent) {
        if (simpleObject.getPeriod() == 0) {
            runSimpleObject(simpleObject);
        } else {
            scheduleNextRun(simpleObject);
            runPeriodicSimpleObjectIfNameMatches(simpleObject, intent);
        }
    }

    private void runSimpleObject(SimpleObject simpleObject) {
        List<SimpleObject.EventType> events = simpleObject.getEvents();

        boolean areAllEventsVerified = true;
        if (events != null) {
            for (SimpleObject.EventType event : events) {
                boolean isEventVerified = false;
                switch (event) {
                    case SCREEN_ON:
                        isEventVerified = new ScreenOn(this).isEventVerified();
                        break;
                    case SCREEN_OFF:
                        isEventVerified = new ScreenOff(this).isEventVerified();
                        break;
                    default:
                        isEventVerified = false;
                        break;
                }

                if (!isEventVerified) {
                    areAllEventsVerified = false;
                    event.getValue().register(this);
                }
            }
        }

        if (events == null || areAllEventsVerified) {
            if (simpleObject.getPeriod() == 0) {
                DatabaseSimpleObject.getInstance(this).delete(simpleObject.getName());
            }
            simpleObject.run();
        }
    }

    private void runPeriodicSimpleObjectIfNameMatches(SimpleObject simpleObject, Intent intent) {
        String simpleObjectName = simpleObject.getName();
        String periodicSimpleObjectSelected = intent.getStringExtra(DatabaseHelperSimpleObject.Column.COLUMN_NAME.getColumnName());
        if (periodicSimpleObjectSelected != null &&
                !periodicSimpleObjectSelected.isEmpty() &&
                    periodicSimpleObjectSelected.equalsIgnoreCase(simpleObjectName)) {
            runSimpleObject(simpleObject);
        }
    }

    private void scheduleNextRun(SimpleObject simpleObject) {
        Intent objectIntent = new Intent(this, SimpleService.class);
        objectIntent.putExtra(DatabaseHelperSimpleObject.Column.COLUMN_NAME.getColumnName(), simpleObject.getName());
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + simpleObject.getPeriod(),
                PendingIntent.getService(this, 0, objectIntent, 0));
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
