package com.example.todolistapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolistapp.application.TodoListApplication;
import com.example.todolistapp.model.CategoryModel;
import com.example.todolistapp.model.EventModel;

import java.util.ArrayList;

import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_CATEGORY;
import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_CATEGORY_ID;
import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_EVENT_CATEGORY_ID;
import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_EVENT_ID;
import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_EVENT_NAME;
import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_ID;
import static com.example.todolistapp.db.TodoDatabaseHelper.COLUMN_STATUS;
import static com.example.todolistapp.db.TodoDatabaseHelper.TABLE_EVENT;

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

    public void insert(CategoryModel categoryModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CATEGORY_ID, categoryModel.getId());
        contentValues.put(COLUMN_CATEGORY, categoryModel.getCategory());

        database.insert(TodoDatabaseHelper.TABLE_CATEGORY, null, contentValues);

    }

    public void insertEvents(CategoryModel categoryModel) {
        for (EventModel eventModel : categoryModel.getEventList()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_EVENT_ID, eventModel.getId());
            contentValues.put(COLUMN_EVENT_NAME, eventModel.getEventContent());
            contentValues.put(COLUMN_EVENT_CATEGORY_ID, categoryModel.getId());
            contentValues.put(COLUMN_STATUS, eventModel.getStatus());

            database.insert(TABLE_EVENT, null, contentValues);
        }
    }

    public void insertEvent(EventModel eventModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EVENT_ID, eventModel.getId());
        contentValues.put(COLUMN_EVENT_NAME, eventModel.getEventContent());
        contentValues.put(COLUMN_EVENT_CATEGORY_ID, eventModel.getCategoryId());
        contentValues.put(COLUMN_STATUS, eventModel.getStatus());

        database.insert(TABLE_EVENT, null, contentValues);
    }

    public void delete(CategoryModel categoryModel) {
        database.delete(TodoDatabaseHelper.TABLE_CATEGORY,
                COLUMN_CATEGORY_ID + " = " + categoryModel.getId(), null);
    }

    public void deleteEvent(EventModel eventModel) {
        database.delete(TodoDatabaseHelper.TABLE_EVENT,
                COLUMN_EVENT_ID + " = ? and " + COLUMN_EVENT_CATEGORY_ID + " = ?"
                , new String[]{String.valueOf(eventModel.getId()),
                        String.valueOf(eventModel.getCategoryId())});
    }

    public void update(CategoryModel categoryModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, categoryModel.getId());
        contentValues.put(COLUMN_CATEGORY_ID, categoryModel.getId());
        contentValues.put(COLUMN_CATEGORY, categoryModel.getCategory());
        database.update(TodoDatabaseHelper.TABLE_CATEGORY, contentValues, COLUMN_CATEGORY_ID + " " +
                "= " + categoryModel.getId(), null);
    }

    public void updateEvent(EventModel eventModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EVENT_ID, eventModel.getId());
        contentValues.put(COLUMN_EVENT_NAME, eventModel.getEventContent());
        contentValues.put(COLUMN_STATUS, eventModel.getStatus());

        database.update(TodoDatabaseHelper.TABLE_EVENT, contentValues,
                COLUMN_EVENT_ID + " = ? and " + COLUMN_EVENT_CATEGORY_ID + " = ?"
                , new String[]{String.valueOf(eventModel.getId()),
                        String.valueOf(eventModel.getCategoryId())});
    }

    public ArrayList<CategoryModel> queryAll() {
        ArrayList<CategoryModel> list = new ArrayList<>();
        Cursor cursor = database.query(TodoDatabaseHelper.TABLE_CATEGORY, null, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            String categoryId = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID));
            String categoryName = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));

            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(Integer.valueOf(categoryId));
            categoryModel.setCategory(categoryName);

            list.add(categoryModel);
        }

        cursor.close();

        return list;
    }

    public ArrayList<EventModel> queryEvent(String event) {
        ArrayList<EventModel> list = new ArrayList<>();
        String querySql = "SELECT * FROM " + TABLE_EVENT + " WHERE " + COLUMN_EVENT_NAME + " like " +
                "'%" + event + "%'";

        Cursor cursor = database.rawQuery(querySql, null);

        while (cursor.moveToNext()) {
            String eventId = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_ID));
            String eventName = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_NAME));
            String status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS));

            EventModel eventModel = new EventModel();
            eventModel.setId(Integer.valueOf(eventId));
            eventModel.setStatus(status);
            eventModel.setEventContent(eventName);

            list.add(eventModel);
        }

        cursor.close();

        return list;
    }

    public ArrayList<EventModel> queryAllEvents(int categoryId) {
        ArrayList<EventModel> list = new ArrayList<>();
        Cursor cursor = database.query(TABLE_EVENT, null,
                COLUMN_EVENT_CATEGORY_ID + " = " + categoryId, null, null, null, COLUMN_EVENT_NAME);

        while (cursor.moveToNext()) {
            String eventId = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_ID));
            String eventName = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_NAME));
            String status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS));

            EventModel eventModel = new EventModel();
            eventModel.setId(Integer.valueOf(eventId));
            eventModel.setStatus(status);
            eventModel.setEventContent(eventName);

            list.add(eventModel);
        }

        cursor.close();

        return list;
    }

    public void deleteAll() {
        database.delete(TodoDatabaseHelper.TABLE_EVENT, null, null);
    }
}
