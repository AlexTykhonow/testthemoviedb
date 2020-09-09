package com.Test.themoviedb.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class dataLike {
    public static void setLike(Context context, String json) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("Like", json).apply();
    }
    public static String getLike(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("Like", "");
    }
}
