package com.example.simple;

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
    private List<EventType> events;
    public enum EventType {
        SCREEN_ON(new ScreenOnBroadcastReceiver()),
        SCREEN_OFF(new ScreenOffBroadcastReceiver());

        private final SimpleBroadcastReceiver simpleBroadcastReceiver;
        EventType(SimpleBroadcastReceiver simpleBroadcastReceiver) { this.simpleBroadcastReceiver = simpleBroadcastReceiver; }
        public SimpleBroadcastReceiver getValue() { return simpleBroadcastReceiver; }
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    protected long getPeriod() {
        return period;
    }

    public void setEvent(EventType event) {
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

    public List<EventType> getEvents() {
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
