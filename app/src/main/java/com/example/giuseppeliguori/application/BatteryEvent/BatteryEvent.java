package com.example.giuseppeliguori.application.BatteryEvent;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.example.simple.SimpleObject;

/**
 * Created by giuseppeliguori on 07/07/2017.
 */

public class BatteryEvent {

    private Context context;
    protected final static int BATTERY_LOW = 15;

    public BatteryEvent(Context context) {
        this.context = context;
    }

    public float getBatteryLevel() {
        Intent batteryIntent = context.registerReceiver(null, new SimpleObject.SimpleIntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        // Error checking that probably isn't needed but I added just in case.
        if(level == -1 || scale == -1) {
            return 50.0f;
        }

        return ((float)level / (float)scale) * 100.0f;
    }
}
