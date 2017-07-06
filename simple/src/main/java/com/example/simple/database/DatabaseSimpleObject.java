package com.example.simple.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.simple.Simple;
import com.example.simple.SimpleObject;

import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giuseppeliguori on 07/06/2017.
 */

public class DatabaseSimpleObject {
    private static final String TAG = "DatabaseSimpleObject";

    private static DatabaseSimpleObject instance;

    private static DatabaseHelperSimpleObject databaseHelperSimpleObject;

    public synchronized static DatabaseSimpleObject getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseSimpleObject(context);
        }
        return instance;
    }

    private DatabaseSimpleObject(Context context) {
        databaseHelperSimpleObject = new DatabaseHelperSimpleObject(context);
    }

    public long insert(SimpleObject simpleObject) {
        if (simpleObject == null) { return -1; }

        // Gets the data repository in write mode
        SQLiteDatabase db = databaseHelperSimpleObject.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(DatabaseHelperSimpleObject.Column.COLUMN_NAME.getColumnName(), simpleObject.getName());
        values.put(DatabaseHelperSimpleObject.Column.COLUMN_OBJECT.getColumnName(), SerializationUtils.serialize(simpleObject));
        values.put(DatabaseHelperSimpleObject.Column.COLUMN_TIME.getColumnName(), System.currentTimeMillis());

        // Insert the new row, returning the primary key value of the new row
        return db.insert(DatabaseHelperSimpleObject.TABLE_NAME, null, values);
    }

    public List<SimpleObject> getSimpleObjects() {
        String query = "SELECT  * FROM " + DatabaseHelperSimpleObject.TABLE_NAME;
        SQLiteDatabase db = databaseHelperSimpleObject.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<SimpleObject> objects = null;
        SimpleObject simpleObject = null;
        if (cursor.moveToFirst()) {
            objects = new ArrayList<>();
            do {
                int index = DatabaseHelperSimpleObject.Column.COLUMN_OBJECT.getIndex();
                simpleObject = SerializationUtils.deserialize(cursor.getBlob(index));
                objects.add(simpleObject);
            } while (cursor.moveToNext());
        }
        db.close();
        return objects;
    }

    public long delete(String name) {
        // Gets the data repository in write mode
        SQLiteDatabase db = databaseHelperSimpleObject.getWritableDatabase();

        // Delete the new row, returning the primary key value of the new row
        return db.delete(DatabaseHelperSimpleObject.TABLE_NAME,
                DatabaseHelperSimpleObject.Column.COLUMN_NAME.getColumnName() + "=?",
                new String[]{name});
    }
}
