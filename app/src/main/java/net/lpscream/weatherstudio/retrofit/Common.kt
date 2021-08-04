package net.lpscream.weatherstudio.retrofit

object Common {
    private val BASE_URL = "http://api.openweathermap.org/"
    val retrofitService: RetrofitService get() = RetrofitClient
        .getClient(BASE_URL)
        .create(RetrofitService::class.java)
}