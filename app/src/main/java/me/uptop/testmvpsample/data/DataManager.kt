package me.uptop.testmvpsample.data

import android.annotation.SuppressLint

import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.realm.RealmResults
import me.uptop.testmvpsample.data.network.RestService
import me.uptop.testmvpsample.data.network.response.WeatherResponse
import me.uptop.testmvpsample.data.storage.RealmManager
import me.uptop.testmvpsample.data.storage.realm.CityRealm
import me.uptop.testmvpsample.data.storage.realm.WeatherCityRealm

import me.uptop.testmvpsample.data.network.NetworkHelper.APP_ID

@Singleton
class DataManager @Inject constructor(private val mPreferencesManager: PreferenceManager,
                                      private val restService: RestService,
                                      private val realmManager: RealmManager) {

    val cities: Observable<RealmResults<CityRealm>>
        get() {
            if (realmManager.citiesSize == 0) {
                realmManager.addCities(defaultCities)
            }

            return Observable.just(realmManager.cities)
        }

    private val defaultCities: ArrayList<String>
        get() {
            val defaultCities = ArrayList<String>()
            defaultCities.add("Moscow")
            defaultCities.add("London")
            return defaultCities
        }

    fun getWeatherData(country: String): Observable<WeatherCityRealm> {
        return getWeatherFromServer(country)
                .flatMap { it -> realmManager.getWeatherCityData(country) }
                .distinctUntilChanged()
                .cache()
    }

    private fun getWeatherFromServer(country: String): Observable<WeatherResponse> {
        return restService.getWeather(country, APP_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { realmManager.saveWeatherCityResponseToRealm(it) }
                .toObservable()
    }

    @SuppressLint("CheckResult")
    fun addCity(country: String): Observable<WeatherResponse> {
        return if (realmManager.getCity(country)) {
            Observable.error(Exception())
        } else getWeatherFromServer(country)
                .doOnComplete { realmManager.addCity(country) }

    }
}
