package com.example.todolistapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TodoDatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_EVENT = "event";
    public static final String TABLE_CATEGORY = "category";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CATEGORY_ID = "category_id";

    public static final String COLUMN_EVENT_ID = "event_id";
    public static final String COLUMN_EVENT_CATEGORY_ID = "event_category_id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_EVENT_NAME = "event_name";
    public static final String COLUMN_STATUS = "status";

    private static final String DATABASE_NAME = "todolist.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TAG = "TodoDatabaseHelper";

    private static final String DATABASE_CREATE_CATEGORY_TABLE = "create table if not exists "
            + TABLE_CATEGORY + "( " + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_CATEGORY_ID + " text,"
            + COLUMN_CATEGORY + " text"
            + ");";

    private static final String DATABASE_CREATE_EVENT_TABLE = "create table if not exists "
            + TABLE_EVENT + "( " + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_EVENT_ID + " text,"
            + COLUMN_EVENT_CATEGORY_ID + " text,"
            + COLUMN_EVENT_NAME + " text,"
            + COLUMN_STATUS + " text,"
            + "FOREIGN KEY ("+COLUMN_EVENT_CATEGORY_ID+") REFERENCES "+ COLUMN_CATEGORY_ID
            + ");";

    public TodoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: ");
        sqLiteDatabase.execSQL(DATABASE_CREATE_CATEGORY_TABLE);
        sqLiteDatabase.execSQL(DATABASE_CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        onCreate(sqLiteDatabase);
    }
}
