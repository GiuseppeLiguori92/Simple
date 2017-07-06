package com.example.simple.events.screen;

import android.content.Context;
import android.os.Build;

import com.example.simple.events.Event;

/**
 * Created by giuseppeliguori on 06/07/2017.
 */

public class ScreenOff extends ScreenEvent implements Event {

    public ScreenOff(Context context) {
        super(context);
    }

    @Override
    public boolean isEventVerified() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            return !getPowerManager().isInteractive();
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH) {
            return !getPowerManager().isScreenOn();
        }

        return false;
    }
}
