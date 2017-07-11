package com.example.giuseppeliguori.application.BatteryEvent;

import android.content.Context;

import com.example.simple.events.SimpleEvent;

/**
 * Created by giuseppeliguori on 07/07/2017.
 */

public class BatteryOkay extends BatteryEvent implements SimpleEvent {

    public BatteryOkay(Context context) {
        super(context);
    }

    @Override
    public boolean isEventVerified() {
        return getBatteryLevel() > BATTERY_LOW;
    }
}
