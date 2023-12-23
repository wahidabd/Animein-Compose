package com.wahidabd.animeku.utils

import android.content.Context
import com.wahidabd.animeku.domain.user.model.User
import com.wahidabd.animeku.utils.constants.Constants


/**
 * Created by wahid on 12/22/2023.
 * Github github.com/wahidabd.
 */


class PreferenceManager (context: Context){

    private val pref = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    fun setLogin(value: Boolean){
        pref.edit().putBoolean(Constants.PREF_LOGIN, value).apply()
    }

    fun setUser(user: User) = with(pref.edit()){
        putString(Constants.PREF_UID, user.uid)
        putString(Constants.PREF_USERNAME, user.username)
        putString(Constants.PREF_EMAIL, user.email)
        putString(Constants.PREF_PHOTO, user.photo)
        apply()
    }

    fun getLogin(): Boolean = pref.getBoolean(Constants.PREF_LOGIN, false)

    fun getUser(): User = User(
        uid = pref.getString(Constants.PREF_UID, "").orEmpty(),
        username = pref.getString(Constants.PREF_USERNAME, "").orEmpty(),
        email = pref.getString(Constants.PREF_EMAIL, "").orEmpty(),
        photo = pref.getString(Constants.PREF_PHOTO, "").orEmpty()
    )
}