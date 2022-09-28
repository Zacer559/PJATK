package com.s16941.tookandsave.models

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.s16941.tookandsave.activities.MainActivity

// Holds updating settings
class SettingsModel {

    private val activity: AppCompatActivity = MainActivity.ACTIVITY as AppCompatActivity
    private val resources: Resources = this.activity.applicationContext.resources
    private val pref: SharedPreferences =
        this.activity.getSharedPreferences("mypref", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = pref.edit()


    var range: Int = 0
        get() {
            Log.d("sharedPref", "get notify Range " + pref.getInt("range", 1))
            return pref.getInt("range", 100);
        }
        set(value) {
            Log.d("sharedPref", "set notifRange " + value)
            field = value
            editor.putInt("range", value);
            editor.apply()
            editor.commit()
        }

    var size: Int = 0
        get() {
            return pref.getInt("size", 22);
        }
        set(value) {
            Log.d("sharedPref", "set size " + value)
            field = value
            editor.putInt("size", value);
            editor.apply()
            editor.commit()
        }

    var color: Int = 0
        get() {
            return pref.getInt("color", 0);
        }
        set(value) {
            Log.d("sharedPref", "set color " + value)
            field = value
            editor.putInt("color", value);
            editor.apply()
            editor.commit()
        }


    fun getNewColor(): Int {
        var newColor = Color.RED
        if (color == 0) {
            newColor = Color.RED
        }
        if (color == 1) {
            newColor = Color.GREEN
        }
        if (color == 2) {
            newColor = Color.BLUE
        }
        if (color == 3) {
            newColor = Color.BLACK
        }
        if (color == 4) {
            newColor = Color.WHITE
        }
        if (color == 5) {
            newColor = Color.YELLOW
        }
        if (color == 5) {
            newColor = Color.MAGENTA
        }
        return newColor
    }


}