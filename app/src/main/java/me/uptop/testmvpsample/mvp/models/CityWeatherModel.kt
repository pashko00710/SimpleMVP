package me.uptop.testmvpsample.mvp.models

import io.reactivex.Observable
import io.realm.RealmResults
import me.uptop.testmvpsample.data.storage.realm.WeatherCityRealm

interface CityWeatherModel : BaseModel {
    fun getWeatherCityData(country: String): Observable<WeatherCityRealm>
}
