package com.psg.Presentation.Util

import android.content.Context

object PreferenceHelper {

    private const val PREF_NAME = "settings"
    private const val KEY_TRIGGER_WORD = "trigger_word"
    private const val DEFAULT_TRIGGER = "emergency"

    fun setTriggerWord(context: Context, word: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_TRIGGER_WORD, word).apply()
    }

    fun getTriggerWord(context: Context): String {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_TRIGGER_WORD, DEFAULT_TRIGGER) ?: DEFAULT_TRIGGER
    }
}
