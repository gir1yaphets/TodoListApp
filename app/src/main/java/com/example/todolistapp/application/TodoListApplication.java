package com.example.todolistapp.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class TodoListApplication extends Application {
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
