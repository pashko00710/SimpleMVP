package me.uptop.testmvpsample.data.network.response.dto

import me.uptop.testmvpsample.data.network.response.WeatherResponse
import me.uptop.testmvpsample.data.network.response.model.*


class WeatherDto(weatherResponse: WeatherResponse) {
    var coord: Coord? = null
    var weather: List<Weather>? = null
    var base: String? = null
    var main: Main? = null
    var visibility: Int = 0
    var wind: Wind? = null
    var clouds: Clouds? = null
    var id: Int = 0
    var name: String? = null
    var cod: Int = 0

    init {
        coord = weatherResponse.coord
        weather = weatherResponse.weather
        base = weatherResponse.base
        main = weatherResponse.main
        visibility = weatherResponse.visibility
        wind = weatherResponse.wind
        clouds = weatherResponse.clouds
        id = weatherResponse.id
        name = weatherResponse.name
        cod = weatherResponse.cod
    }
}