package com.example.simple;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.simple.events.screen.ScreenOffBroadcastReceiver;
import com.example.simple.events.screen.ScreenOnBroadcastReceiver;
import com.example.simple.events.SimpleBroadcastReceiver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giuseppeliguori on 7/5/17.
 */

public abstract class SimpleObject implements Serializable {
    private String name;
    private long period;
    private List<SimpleEventType> events;

    public static class SimpleEventType implements Serializable {

        private Context context;
        private final SimpleBroadcastReceiver simpleBroadcastReceiver;

        public SimpleEventType(Context context, SimpleBroadcastReceiver simpleBroadcastReceiver) {
            this.context = context;
            this.simpleBroadcastReceiver = simpleBroadcastReceiver;
        }

        protected SimpleEventType(SimpleBroadcastReceiver simpleBroadcastReceiver) {
            this.simpleBroadcastReceiver = simpleBroadcastReceiver;
        }

        public SimpleBroadcastReceiver getValue() {
            return simpleBroadcastReceiver;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class SimpleIntentFilter extends IntentFilter implements Serializable {
        public SimpleIntentFilter() {
            super();
        }

        public SimpleIntentFilter(String action) {
            super(action);
        }

        public SimpleIntentFilter(String action, String dataType) throws MalformedMimeTypeException {
            super(action, dataType);
        }

        public SimpleIntentFilter(IntentFilter o) {
            super(o);
        }
    }

    public static final SimpleEventType SCREEN_ON = new SimpleEventType(new ScreenOnBroadcastReceiver(new SimpleIntentFilter(Intent.ACTION_SCREEN_ON)));
    public static final SimpleEventType SCREEN_OFF = new SimpleEventType(new ScreenOffBroadcastReceiver(new SimpleIntentFilter(Intent.ACTION_SCREEN_OFF)));
//
//    public enum SimpleEventType {
//        SCREEN_ON(new ScreenOnBroadcastReceiver()),
//        SCREEN_OFF(new ScreenOffBroadcastReceiver());
//
//        private final SimpleBroadcastReceiver simpleBroadcastReceiver;
//        SimpleEventType(SimpleBroadcastReceiver simpleBroadcastReceiver) { this.simpleBroadcastReceiver = simpleBroadcastReceiver; }
//        public SimpleBroadcastReceiver getValue() { return simpleBroadcastReceiver; }
//    }

    public void setPeriod(int period) {
        this.period = period;
    }

    protected long getPeriod() {
        return period;
    }

    public void setEvent(SimpleEventType event) {
        if (event == null) { return; }

        if (events == null) {
            events = new ArrayList<>();
        }

        if (events.contains(event)) {
            return;
        } else {
            events.add(event);
        }
    }

    public List<SimpleEventType> getEvents() {
        return events;
    }

    public SimpleObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Use this method to define what you want to run
     * It will run in the main thread
     */
    public abstract void run();
}
