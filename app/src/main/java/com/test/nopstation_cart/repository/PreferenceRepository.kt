package com.test.nopstation_cart.repository

import android.content.SharedPreferences

class PreferenceRepository(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val TOKEN_KEY = "Token"
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, "")
    }
}
