package com.example.simple.events.screen;

import android.content.Context;
import android.os.PowerManager;

/**
 * Created by giuseppeliguori on 06/07/2017.
 */

public class ScreenEvent {
    private PowerManager powerManager;
    public ScreenEvent(Context context) {
        powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    }

    public PowerManager getPowerManager() {
        return powerManager;
    }
}
