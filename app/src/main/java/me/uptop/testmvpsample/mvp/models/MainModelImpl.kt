package me.uptop.testmvpsample.mvp.models

import io.reactivex.Observable
import io.realm.RealmResults
import me.uptop.testmvpsample.data.DataManager
import me.uptop.testmvpsample.data.network.response.WeatherResponse
import me.uptop.testmvpsample.data.storage.realm.CityRealm
import javax.inject.Inject

class MainModelImpl @Inject constructor(private val dataManager: DataManager) : MainModel {
    override val cities: Observable<RealmResults<CityRealm>>
        get() = dataManager.cities

    override fun addCity(country: String) : Observable<WeatherResponse> {
        return dataManager.addCity(country)
    }
}
