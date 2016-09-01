package com.ftd.keal.keal_mvvm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sdhuang on 16/9/1 16:22.
 */
public class PreferencesUtil {
    private final static String FILE_NAME = "settings";
    private final static String MODE = "day_night_mode";

    public static void saveDayNightMode(Context context, boolean isDay) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(FILE_NAME,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(MODE, isDay);
        editor.commit();
    }

    public static boolean getDayNightMode(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,
                Activity.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MODE,true);
    }
}
