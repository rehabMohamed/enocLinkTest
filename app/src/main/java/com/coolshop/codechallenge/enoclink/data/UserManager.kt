package com.coolshop.codechallenge.enoclink.data

import android.content.SharedPreferences
import com.google.gson.Gson

class UserManager(private val sharedPrefs: SharedPreferences, private val gson: Gson) {

    val USER_KEY = "user"

    // in-memory cache of the loggedInUser object
    var loggedInUser: LoggedInUser? =
            gson.fromJson(sharedPrefs.getString(USER_KEY, null), LoggedInUser::class.java)

    val isLoggedIn: Boolean
        get() = loggedInUser != null


    fun updateUser(userId: String, apiToken: String, email: String, password: String) {
        // app shouldn't store password locally, saving only for the purpose of displaying the password for this code challenge!
        this.loggedInUser = LoggedInUser(userId = userId, apiToken = apiToken, email = email, password = password)

        sharedPrefs.edit().putString(USER_KEY, gson.toJson(loggedInUser)).apply()
    }

    fun updateAvatar(avatarUrl: String?) {
        loggedInUser?.avatarUrl = avatarUrl

        sharedPrefs.edit().putString(USER_KEY, gson.toJson(loggedInUser)).apply()
    }

    fun getUserId(): String? = loggedInUser?.userId
}
