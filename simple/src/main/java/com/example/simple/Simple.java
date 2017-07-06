package com.example.simple;

import android.content.Context;
import android.content.Intent;

import com.example.simple.database.DatabaseSimpleObject;

/**
 * Created by giuseppeliguori on 7/5/17.
 */

public class Simple {
    private static Simple instance;

    public static Simple getnstance() {
        if (instance == null) {
            instance = new Simple();
        }
        return instance;
    }

    private Simple() {}

    public void addObject(Context context, SimpleObject simpleObject) {
        if (context == null) { return; }
        if (simpleObject == null) { return; }

        DatabaseSimpleObject databaseSimpleObject = DatabaseSimpleObject.getInstance(context);
        databaseSimpleObject.insert(simpleObject);

        Intent intent = new Intent(context, SimpleService.class);
        context.startService(intent);
    }
}
