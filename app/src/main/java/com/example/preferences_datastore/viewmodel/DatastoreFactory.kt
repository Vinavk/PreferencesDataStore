package com.example.preferences_datastore.viewmodel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class DatastoreFactory(
    private val dataStore: DataStore<Preferences>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DataViewModel(dataStore) as T
    }

}