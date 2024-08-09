package com.example.preferences_datastore.viewmodel

import android.annotation.SuppressLint
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


@Suppress("UNCHECKED_CAST")
class DataViewModel(private var dataStore: DataStore<Preferences>) : ViewModel() {

    @SuppressLint("SuspiciousIndentation")
    fun savedata(key: String, value: String) {
        viewModelScope.launch {
            val preferncekey = stringPreferencesKey(key)
            dataStore.edit {

                it[preferncekey] = value

            }
        }

    }


    @SuppressLint("SuspiciousIndentation")
    suspend fun showdata(key: String): String {
        val keyDate = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[keyDate] ?: ""
    }


    fun getAllData(): Flow<Preferences> {
        return dataStore.data
    }


   fun deletedata(key : String){
       viewModelScope.launch {
           var keydata = stringPreferencesKey(key)

           dataStore.edit {
               it.remove(keydata)
           }
       }

    }

}


