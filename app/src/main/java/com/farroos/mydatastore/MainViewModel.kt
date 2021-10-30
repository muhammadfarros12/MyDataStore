package com.farroos.mydatastore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val pref: SettingPreferences): ViewModel() {

    fun getThemeSettings(): LiveData<Boolean>{
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }

}

/**
 * ika diperhatikan kembali,  terdapat constructor pada ViewModel. Perlu diketahui, kita tidak bisa membuat ViewModel secara langsung.
 * Untuk itu, kita akan membuat class yang extend ke ViewModelProvider
 * untuk bisa memasukkan constructor ke dalam ViewModel.
 * Buatlah class baru dengan nama ViewModelFactory
 * */