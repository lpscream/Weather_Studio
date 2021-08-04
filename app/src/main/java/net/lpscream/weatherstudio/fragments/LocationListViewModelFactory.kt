package net.lpscream.weatherstudio.fragments

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LocationListViewModelFactory(private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationListViewModel(sharedPreferences) as T
    }
}