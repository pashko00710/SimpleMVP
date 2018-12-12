package me.uptop.testmvpsample.data.network

import io.reactivex.Single
import me.uptop.testmvpsample.data.network.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RestService {
    @GET("data/2.5/weather")
    fun getWeather(@Query("q") country: String, @Query("appid") appId: String): Single<WeatherResponse>
}
