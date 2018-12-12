package me.uptop.testmvpsample.data

import android.content.Context
import android.content.SharedPreferences

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(context: Context) {
    private val mSharedPreferences: SharedPreferences

    init {
        this.mSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context)
    }
}
