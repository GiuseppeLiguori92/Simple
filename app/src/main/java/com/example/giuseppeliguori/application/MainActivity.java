package com.example.giuseppeliguori.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.simple.Simple;
import com.example.simple.SimpleObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleObject counter = new CounterExecutor(10);
        Simple.getnstance().addObject(this, counter);

        SimpleObject counter2 = new CounterExecutor(3);
        counter2.setPeriod(60000);

        Simple.getnstance().addObject(this, counter2);

    }
}
