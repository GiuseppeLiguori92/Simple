package com.example.simple.events.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.simple.SimpleObject;
import com.example.simple.events.SimpleBroadcastReceiver;

/**
 * Created by giuseppeliguori on 06/07/2017.
 */

public class ScreenOnBroadcastReceiver extends SimpleBroadcastReceiver {
    public ScreenOnBroadcastReceiver(SimpleObject.SimpleIntentFilter intentFilter) {
        super(intentFilter);
    }
}
