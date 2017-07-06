package com.example.simple.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.simple.database.DatabaseHelperSimpleObject.Column.COLUMN_NAME;
import static com.example.simple.database.DatabaseHelperSimpleObject.Column.COLUMN_OBJECT;
import static com.example.simple.database.DatabaseHelperSimpleObject.Column.COLUMN_TIME;

/**
 * Created by giuseppeliguori on 07/06/2017.
 */

public class DatabaseHelperSimpleObject extends SQLiteOpenHelper {

    protected static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "DatabaseSimpleObject";

    protected static final String TABLE_NAME          = "SimpleObjects";

    public static enum Column {
        COLUMN_ID("Id", 0),
        COLUMN_NAME("Name", 1),
        COLUMN_OBJECT("Object", 2),
        COLUMN_TIME("Time", 3);

        private final String column;
        private final int index;
        Column(String column, int index) {
            this.column = column;
            this.index = index;
        }
        public String getColumnName() { return column; }
        public int getIndex() { return index; }
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    Column.COLUMN_ID.getColumnName() + " INTEGER PRIMARY KEY," +
                    Column.COLUMN_NAME.getColumnName() + " TEXT NOT NULL UNIQUE," +
                    Column.COLUMN_OBJECT.getColumnName() + " TEXT," +
                    Column.COLUMN_TIME.getColumnName() + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelperSimpleObject(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
