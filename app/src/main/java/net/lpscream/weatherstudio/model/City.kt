package net.lpscream.weatherstudio.model

data class City(
    var coord: Coord,
    var weather: ArrayList<Weather>,
    var base: String,
    var main: Main,
    var visibility: String,
    var wind: Wind,
    var clouds: Clouds,
    var dt: String,
    var sys: Sys,
    var timezone: String,
    var id: String,
    var name: String,
    var cod: String
)


data class Sys(
    var country: String,
    var sunrise: String,
    var sunset: String
)

data class Clouds(
    var all: String
)


data class Wind(
    var speed: String,
    var deg: String,
    var gust: String
)


data class Main(
    var temp: String,
    var feels_like: String,
    var temp_min: String,
    var temp_max: String,
    var pressure: String,
    var humidity: String,
    var sea_level: String,
    var grnd_level: String
)


data class Weather(
    var id: String,
    var main: String,
    val description: String,
    var icon: String
)


data class Coord(
    var lon: String,
    var lat: String
)



