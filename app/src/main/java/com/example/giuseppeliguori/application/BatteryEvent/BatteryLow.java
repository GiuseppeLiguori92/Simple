package com.example.giuseppeliguori.application.BatteryEvent;

import android.content.Context;

import com.example.simple.events.SimpleEvent;

/**
 * Created by giuseppeliguori on 07/07/2017.
 */

public class BatteryLow extends BatteryEvent implements SimpleEvent {

    public BatteryLow(Context context) {
        super(context);
    }

    @Override
    public boolean isEventVerified() {
        return getBatteryLevel() <= BATTERY_LOW;
    }
}
