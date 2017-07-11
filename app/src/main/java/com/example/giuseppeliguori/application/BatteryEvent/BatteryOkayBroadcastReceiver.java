package com.example.giuseppeliguori.application.BatteryEvent;
import com.example.simple.SimpleObject;
import com.example.simple.events.SimpleBroadcastReceiver;

/**
 * Created by giuseppeliguori on 06/07/2017.
 */

public class BatteryOkayBroadcastReceiver extends SimpleBroadcastReceiver {
    public BatteryOkayBroadcastReceiver(SimpleObject.SimpleIntentFilter intentFilter) {
        super(intentFilter);
    }
}
