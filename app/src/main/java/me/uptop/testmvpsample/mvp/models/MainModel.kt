package me.uptop.testmvpsample.mvp.models

import io.reactivex.Observable
import io.realm.RealmResults
import me.uptop.testmvpsample.data.network.response.WeatherResponse
import me.uptop.testmvpsample.data.storage.realm.CityRealm

interface MainModel : BaseModel {
    val cities: Observable<RealmResults<CityRealm>>
    fun addCity(country: String): Observable<WeatherResponse>
}
