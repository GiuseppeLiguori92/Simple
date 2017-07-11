package com.example.giuseppeliguori.application.BatteryEvent;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.simple.SimpleObject;
import com.example.simple.events.SimpleBroadcastReceiver;

/**
 * Created by giuseppeliguori on 06/07/2017.
 */

public class BatteryLowBroadcastReceiver extends SimpleBroadcastReceiver {
    public BatteryLowBroadcastReceiver(SimpleObject.SimpleIntentFilter intentFilter) {
        super(intentFilter);
    }
}
