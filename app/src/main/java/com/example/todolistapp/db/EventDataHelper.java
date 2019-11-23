package com.example.todolistapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolistapp.application.TodoListApplication;
import com.example.todolistapp.model.EventModel;

import java.util.ArrayList;

import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_DETAIL;
import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_ID;
import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_STATUS;
import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_SUMMARY;

public class EventDataHelper {
    private SQLiteDatabase database;
    private TodoDatabaseHelper databaseHelper;

    private static EventDataHelper eventDataHelper;

    private EventDataHelper() {
        databaseHelper = new TodoDatabaseHelper(TodoListApplication.getContext());
        database = databaseHelper.getWritableDatabase();
    }

    public static EventDataHelper getInstance() {
        if (eventDataHelper == null) {
            synchronized (EventDataHelper.class) {
                if (eventDataHelper == null) {
                    eventDataHelper = new EventDataHelper();
                }
            }
        }

        return eventDataHelper;
    }

    public void insert(EventModel eventModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, eventModel.getId());
        contentValues.put(COLUMN_SUMMARY, eventModel.getSummaryTitle());
        contentValues.put(COLUMN_DETAIL, eventModel.getDetailContent());
        contentValues.put(COLUMN_STATUS, eventModel.getStatus());

        database.insert(TodoDatabaseHelper.TABLE_EVENT, null, contentValues);
    }

    public void delete(EventModel eventModel) {
        database.delete(TodoDatabaseHelper.TABLE_EVENT, TodoDatabaseHelper.COLUMN_ID + " = " + eventModel.getId(), null);
    }

    public void update(EventModel eventModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, eventModel.getId());
        contentValues.put(COLUMN_SUMMARY, eventModel.getSummaryTitle());
        contentValues.put(COLUMN_DETAIL, eventModel.getDetailContent());
        contentValues.put(COLUMN_STATUS, eventModel.getStatus());
        database.update(TodoDatabaseHelper.TABLE_EVENT, contentValues, TodoDatabaseHelper.COLUMN_ID + " = " + eventModel.getId(), null);
    }

    public ArrayList<EventModel> queryAll() {
        ArrayList<EventModel> list = new ArrayList<>();
        Cursor cursor = database.query(TodoDatabaseHelper.TABLE_EVENT, null, null, null, null, null, COLUMN_SUMMARY);

        while (cursor.moveToNext()) {
            String summary = cursor.getString(cursor.getColumnIndex(COLUMN_SUMMARY));
            String detail = cursor.getString(cursor.getColumnIndex(COLUMN_DETAIL));
            String status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS));

            EventModel eventModel = new EventModel();
            eventModel.setSummaryTitle(summary);
            eventModel.setDetailContent(detail);
            eventModel.setStatus(status);

            list.add(eventModel);
        }

        cursor.close();

        return list;
    }

    public void deleteAll() {
        database.delete(TodoDatabaseHelper.TABLE_EVENT, null, null);
    }
}
