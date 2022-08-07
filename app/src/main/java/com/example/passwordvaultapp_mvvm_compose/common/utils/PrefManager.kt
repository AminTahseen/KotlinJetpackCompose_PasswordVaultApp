package com.example.passwordvaultapp_mvvm_compose.common.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    private val passCodeKey="PASSCODE_KEY"
    private val appPref = "pref1"

    private val preferences: SharedPreferences = context.getSharedPreferences(appPref,Context.MODE_PRIVATE)

    var passCodeValue: String
        get() = preferences.getString(passCodeKey,"00000").toString()
        set(value) = preferences.edit().putString(passCodeKey, value).apply()
}