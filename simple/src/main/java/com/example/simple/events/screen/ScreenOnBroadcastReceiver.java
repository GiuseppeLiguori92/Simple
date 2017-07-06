package com.example.simple.events.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.simple.events.SimpleBroadcastReceiver;

/**
 * Created by giuseppeliguori on 06/07/2017.
 */

public class ScreenOnBroadcastReceiver extends SimpleBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            System.out.println("ScreenOnBroadcastReceiver.onReceive: Screen went ON");
            super.onReceive(context, intent);
        }
    }
}
