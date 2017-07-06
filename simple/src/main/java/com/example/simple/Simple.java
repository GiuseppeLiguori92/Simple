package com.example.simple;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giuseppeliguori on 7/5/17.
 */

public class Simple {
    private static Simple instance;

    public static Simple getnstance() {
        if (instance == null) {
            instance = new Simple();
        }
        return instance;
    }

    private Simple() {}

    public void addObject(Context context, SimpleObject simpleObject) {
        if (context == null) { return; }
        if (simpleObject == null) { return; }
        Intent intent = new Intent(context, SimpleService.class);
        Bundle bundle = new Bundle();
        bundle.putByteArray(SimpleService.OBJECT, SerializationUtils.serialize(simpleObject));
        intent.putExtras(bundle);

        if (simpleObject.getPeriod() != 0) {
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            manager.setRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(),
                    simpleObject.getPeriod(),
                    PendingIntent.getService(context, 0, intent, 0));
        } else {
            context.startService(intent);
        }
    }
}
