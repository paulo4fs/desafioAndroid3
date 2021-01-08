package com.paulo.myapplication.authentication.utils

import android.content.Context
import com.paulo.myapplication.authentication.utils.Constants.APP_KEY
import com.paulo.myapplication.authentication.utils.Constants.CHECKED_PREF

object AuthUtils {

    fun savePref(context: Context, isChecked: Boolean) {
        val preferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        preferences.edit().putBoolean(CHECKED_PREF, isChecked).apply()
    }

    fun checkedSavedPref(context: Context): Boolean {
        val preferences = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        return preferences.getBoolean(CHECKED_PREF, false)
    }
}