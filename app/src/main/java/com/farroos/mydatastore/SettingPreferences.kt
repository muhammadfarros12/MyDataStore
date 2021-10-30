package com.farroos.mydatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * tambahkan constructor DataStore dan buat fungsi berikut untuk membuat class ini menjadi Singleton (1)
 * */
class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    /**
     * membaca dan menyimpan data pengaturan tema yang berupa Boolean ke dalam DataStore,
     * lengkapi SettingPreference menjadi seperti berikut:(2)
     * */
    //2
    private val THEME_KEY = booleanPreferencesKey("theme_key")
    //2
    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }
    //2
    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit {
            it[THEME_KEY] = isDarkModeActive
        }
    }

    /**
     * 1
     * */
    companion object {
        @Volatile
        private var INSTANCE: SettingPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }


}