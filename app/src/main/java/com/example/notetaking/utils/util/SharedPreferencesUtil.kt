package com.example.notetaking.utils.util

import com.example.notetaking.MyApplication

object SharedPreferencesUtil {

    private val sharedPreferences =
        MyApplication.getAppContext()?.getSharedPreferences("app_data", 0)

    fun getUserId(): String? {
        return sharedPreferences?.getString("user_id", null)
    }

    fun setUserId(userId: String) {
        sharedPreferences?.apply {
            edit().putString("user_id", userId).apply()
        }
    }

    fun clearUserId() {
        sharedPreferences?.apply {
            edit().remove("user_id").apply()
        }
    }
}