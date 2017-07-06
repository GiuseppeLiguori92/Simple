package com.example.simple.events.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.simple.events.SimpleBroadcastReceiver;

/**
 * Created by giuseppeliguori on 06/07/2017.
 */

public class ScreenOffBroadcastReceiver extends SimpleBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            System.out.println("ScreenOnBroadcastReceiver.onReceive: Screen went OFF");
            super.onReceive(context, intent);
        }
    }
}
