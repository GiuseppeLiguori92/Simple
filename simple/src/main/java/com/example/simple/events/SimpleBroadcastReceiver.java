package com.example.simple.events;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.simple.SimpleService;

/**
 * Created by giuseppeliguori on 06/07/2017.
 */

public class SimpleBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, SimpleService.class));
    }

    enum Status {
        REGISTERED,
        UNREGISTERED
    }

    private Status status = Status.UNREGISTERED;

    public void register(Context context) {
        if (status == Status.UNREGISTERED) {
            status = Status.REGISTERED;
            IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            context.registerReceiver(this, filter);
        }
    }

    public void unregister(Context context) {
        if (status == Status.REGISTERED) {
            context.unregisterReceiver(this);
        }
    }
}
