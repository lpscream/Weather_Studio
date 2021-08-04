package net.lpscream.weatherstudio.fragments

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.lpscream.weatherstudio.model.City
import net.lpscream.weatherstudio.retrofit.Common
import net.lpscream.weatherstudio.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class LocationListViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    private var retrofit: RetrofitService? = null
    private val TAG = "LocationListViewModel_log"
    private lateinit var countrySet: Map<String, *>
    private var countryList: MutableLiveData<List<String>> = MutableLiveData<List<String>>()



init {
    createRetrofit()
    getPref()
}


    fun getByName(name: String){
        retrofit?.getCountryByName(name)?.enqueue(object : Callback<City>{
            override fun onResponse(call: Call<City>, response: Response<City>) {
                savePref(response.body()?.name.toString(), response.body()?.id.toString())
                getPref()
            }

            override fun onFailure(call: Call<City>, t: Throwable) {
                Log.d(TAG, "onFailure: something went wrong!!!")
                Log.d(TAG, "onFailure: " + t.message)
            }

        })
    }

    fun getIdByName(name : String): String{
        return countrySet.filterValues { it ==  name}.keys.toString().substring(1, 7)
    }


    fun getPref(){
        countrySet = sharedPreferences.all
        var values = ArrayList(countrySet.values)
        countryList.postValue(values as ArrayList<String>)
    }

    fun  savePref(name: String, id: String){
        sharedPreferences.edit {
            putString(id, name)
            apply()
        }
    }

    fun removePref(id: String){
        //TODO fix removing city
        Log.d(TAG, "removePref: " + id)
        sharedPreferences.edit {
            remove(id)
            commit()
        }
        sharedPreferences.all.remove(id)
        getPref()
    }

    private fun createRetrofit() {
        retrofit = Common.retrofitService
    }

    fun getCountryList(): MutableLiveData<List<String>>{
        return countryList
    }
}