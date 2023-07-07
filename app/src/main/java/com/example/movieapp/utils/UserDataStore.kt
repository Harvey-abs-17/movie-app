package com.example.movieapp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStore @Inject constructor (@ApplicationContext private val context :Context){

    companion object{
        val Context.dataStore :DataStore<Preferences> by preferencesDataStore(Constants.DATA_STORE_NAME)
        val userToken  = stringPreferencesKey(Constants.USER_TOKEN)
    }

    suspend fun setUserToken(token :String) = context.dataStore.edit {
        it[userToken] = token
    }

    fun getUserDataStore() = context.dataStore.data.map {
        it[userToken] ?: ""
    }

}