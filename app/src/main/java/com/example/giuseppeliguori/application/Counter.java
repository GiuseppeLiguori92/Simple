package com.example.giuseppeliguori.application;

import com.example.simple.SimpleObject;

/**
 * Created by giuseppeliguori on 7/5/17.
 */

public class Counter {
    int counter;
    public Counter() {
        counter = 0;
    }

    public void increment() {
        counter++;
    }

    public int getValue() {
        return counter;
    }
}
