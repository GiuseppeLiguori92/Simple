package com.example.simple.events;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.simple.SimpleObject;
import com.example.simple.SimpleService;

import java.io.Serializable;

/**
 * Created by giuseppeliguori on 06/07/2017.
 */

public class SimpleBroadcastReceiver extends BroadcastReceiver implements Serializable {

    private SimpleObject.SimpleIntentFilter intentFilter;

    public SimpleBroadcastReceiver(SimpleObject.SimpleIntentFilter intentFilter) {
        this.intentFilter = intentFilter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            if (intentFilter.getAction(0).equalsIgnoreCase(action)) {
                context.startService(new Intent(context, SimpleService.class));
            }
        }
    }

    enum Status {
        REGISTERED,
        UNREGISTERED
    }

    private Status status = Status.UNREGISTERED;

    public void register(Context context) {
        if (status == Status.UNREGISTERED) {
            status = Status.REGISTERED;
            context.registerReceiver(this, intentFilter);
        }
    }

    // TODO
    public void unregister(Context context) {
        if (status == Status.REGISTERED) {
            context.unregisterReceiver(this);
        }
    }
}
