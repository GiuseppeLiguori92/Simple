package com.example.simple;

import java.io.Serializable;

/**
 * Created by giuseppeliguori on 7/5/17.
 */

public abstract class SimpleObject implements Serializable {
    public abstract void run();

    private long period;

    public void setPeriod(int period) {
        this.period = period;
    }

    protected long getPeriod() {
        return period;
    }
}
