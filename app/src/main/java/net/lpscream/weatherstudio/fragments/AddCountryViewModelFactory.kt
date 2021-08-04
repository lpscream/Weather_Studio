package net.lpscream.weatherstudio.fragments

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddCountryViewModelFactory(private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {

    // 2
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddCountryViewModel(sharedPreferences) as T
    }
}