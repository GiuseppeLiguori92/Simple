package com.example.giuseppeliguori.application;

import com.example.simple.SimpleObject;

/**
 * Created by giuseppeliguori on 7/5/17.
 */

public class CounterExecutor extends SimpleObject {

    private int limit;

    public CounterExecutor(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    @Override
    public void run() {
        final Counter counter = new Counter();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter.getValue() < limit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    counter.increment();
                    System.out.println("CounterExecutor.run: " + counter.getValue());
                }
            }
        }).start();
    }
}
