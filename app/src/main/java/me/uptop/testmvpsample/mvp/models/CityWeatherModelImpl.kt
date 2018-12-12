package me.uptop.testmvpsample.mvp.models

import io.reactivex.Observable
import me.uptop.testmvpsample.data.DataManager
import me.uptop.testmvpsample.data.storage.realm.WeatherCityRealm
import javax.inject.Inject

class CityWeatherModelImpl @Inject constructor(private val dataManager: DataManager) : CityWeatherModel {

    override fun getWeatherCityData(country: String): Observable<WeatherCityRealm> {
        return dataManager.getWeatherData(country)
    }
}