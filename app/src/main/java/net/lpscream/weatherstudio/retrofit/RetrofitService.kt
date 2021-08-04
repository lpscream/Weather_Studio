package net.lpscream.weatherstudio.retrofit

import net.lpscream.weatherstudio.model.City
import net.lpscream.weatherstudio.model.WeatherList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    private val API_KEY: String
        get() = "f2f54f7cc3c913066f3c679f79bddedc"

    private val UNITS: String
        get() = "metric"

    private val LANG: String
        get() = "ru"

    private val CNT: String
    get() = "16"


    @GET("data/2.5/weather?")
    fun getCountryByName(
        @Query("q") q: String,
        @Query("appid") appid: String = API_KEY,
        @Query("units") units: String = UNITS,
        @Query("lang") lang: String = LANG
    ): Call<City>

    @GET("data/2.5/weather?")
    fun getCountryById(
        @Query("id") id: String,
        @Query("appid") appid: String = API_KEY,
        @Query("units") units: String = UNITS,
        @Query("lang") lang: String = LANG
    ): Call<City>


    @GET("data/2.5/forecast?")
    fun getWetherForecastById(
        @Query("id") id: String,
        @Query("cnt") cnt:String = CNT,
        @Query("appid") appid: String = API_KEY,
        @Query("units") units: String = UNITS,
        @Query("lang") lang: String = LANG
    ): Call<WeatherList>
}