package net.lpscream.weatherstudio.model


data class WeatherList(var list: kotlin.collections.List<List>, var city: CityWeather)


data class List(
    var dt: Long,
    var main: MainWeather,
    var weather: kotlin.collections.List<Weather>,
    var clouds: Clouds,
    var wind: Wind,
    var visibility: String,
    var pop: String,
    var sys: SysWeather,
    var dt_txt: String
)


data class MainWeather(
    var temp: String,
    var feels_like: String,
    var temp_min: String,
    var temp_max: String,
    var pressure: String,
    var sea_level: String,
    var grnd_level: String,
    var humidity: String,
    var temp_kf: String
)

data class SysWeather(var pod: String)


data class CityWeather(
    var id: String,
    var name: String,
    var coord: Coord,
    var country: String,
    var population: String,
    var timezone: String,
    var sunrise: Long,
    var sunset: Long
)




