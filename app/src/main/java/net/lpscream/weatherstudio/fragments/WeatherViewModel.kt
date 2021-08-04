package net.lpscream.weatherstudio.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.lpscream.weatherstudio.model.List
import net.lpscream.weatherstudio.model.WeatherList
import net.lpscream.weatherstudio.retrofit.Common
import net.lpscream.weatherstudio.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(private val id: String) : ViewModel() {
    private var retrofit: RetrofitService? = null
    private val TAG = "WeatherViewModel_log"
    private var weatherList: MutableLiveData<WeatherList> = MutableLiveData()

    init {
        createRetrofit()
        Log.d(TAG, ": " + id)
        getByID(id)
    }


    fun getByID(id: String){
        retrofit?.getWetherForecastById(id)?.enqueue(object : Callback<WeatherList> {
            override fun onResponse(call: Call<WeatherList>, response: Response<WeatherList>) {
                weatherList.postValue(response.body())
            }

            override fun onFailure(call: Call<WeatherList>, t: Throwable) {
                Log.d(TAG, "onFailure: something went wrong!!!")
                Log.d(TAG, "onFailure: " + t.message)
            }
        })
    }


    private fun createRetrofit() {
        retrofit = Common.retrofitService
    }

    fun getWeatherList(): kotlin.collections.List<List>?{
        return weatherList.value?.list
    }

    fun getWeather(): MutableLiveData<WeatherList>{
        return weatherList
    }
}