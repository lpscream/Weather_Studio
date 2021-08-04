package net.lpscream.weatherstudio.fragments

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.lpscream.weatherstudio.model.City
import net.lpscream.weatherstudio.retrofit.Common
import net.lpscream.weatherstudio.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCountryViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    private var retrofit: RetrofitService? = null
    private val TAG = "AddCountryViewModel_log"
    private var countryLIst = MutableLiveData<City>()
        get() = field

    init {
        createRetrofit()
    }


    fun getByName(name: String){
        retrofit?.getCountryByName(name)?.enqueue(object : Callback<City> {
            override fun onResponse(call: Call<City>, response: Response<City>) {
                Log.d(TAG, "onResponse: " + response.body().toString())
            }

            override fun onFailure(call: Call<City>, t: Throwable) {
                Log.d(TAG, "onFailure: something went wrong!!!")
                Log.d(TAG, "onFailure: " + t.message)
            }

        })
    }


    private fun createRetrofit() {
        retrofit = Common.retrofitService
    }

}