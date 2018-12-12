package me.uptop.testmvpsample.data.storage

import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import me.uptop.testmvpsample.data.network.response.WeatherResponse
import me.uptop.testmvpsample.data.storage.realm.*
import java.util.*



class RealmManager {
    private var mRealmInstance: Realm? = null

    private val queryRealmInstance: Realm
        get() {
            if (mRealmInstance == null || mRealmInstance!!.isClosed) {
                mRealmInstance = Realm.getDefaultInstance()
            }

            return mRealmInstance!!
        }

    fun getWeatherCityData(countryName: String) : Observable<WeatherCityRealm>? {
        val entity = queryRealmInstance.where(WeatherCityRealm::class.java)
                .equalTo("name", countryName)
                .findFirst()
        return Observable.just(entity)
    }

    val citiesSize: Int
        get() {
            val list = queryRealmInstance.where(CityRealm::class.java).findAllAsync()
            return list.size
        }

    val cities: RealmResults<CityRealm>
        get() = queryRealmInstance.where(CityRealm::class.java).findAllAsync()

    fun saveWeatherCityResponseToRealm(weatherRes: WeatherResponse) {
        val realm = Realm.getDefaultInstance()

        val weatherRealm = complectAndGetWeatherRealm(weatherRes)

        realm.executeTransaction { realm1 -> realm1.insertOrUpdate(weatherRealm) } //добавляем или обновляем продукт в транзакцию

        realm.close()
    }

    private fun complectAndGetWeatherRealm(weatherRes: WeatherResponse): WeatherCityRealm {
        val list = RealmList<WeatherRealm>()
        for(weather in weatherRes.weather!!) list.add(WeatherRealm(weather.id, weather.main,
                weather.description, weather.icon))

        return WeatherCityRealm(weatherRes.id.toLong(), weatherRes.name,
                CoordRealm(weatherRes.coord?.lon!!, weatherRes.coord?.lat!!), list,
                weatherRes.base, MainRealm(weatherRes.main?.temp!!,
                weatherRes.main?.pressure!!, weatherRes.main?.humidity!!,
                weatherRes.main?.tempMin!!, weatherRes.main?.tempMax!!),
                weatherRes.visibility, WindRealm(weatherRes.wind?.speed!!, weatherRes.wind?.deg!!),
                CloudsRealm(weatherRes.clouds?.all!!), weatherRes.cod)
    }

    fun getCity(country: String): Boolean {
        val entity = queryRealmInstance.where(CityRealm::class.java)
                .equalTo("cityName", country)
                .findFirst()

        if(entity != null) return true

        return false
    }

    fun addCities(defaultCities: ArrayList<String>) {
        val realm = Realm.getDefaultInstance()

        for (defaultCity in defaultCities) {
            val cityRealm = CityRealm(defaultCity)
            realm.executeTransaction { realm1 -> realm1.insertOrUpdate(cityRealm) }
        }

        realm.close()
    }

    fun addCity(defaultCity: String) {
        val realm = Realm.getDefaultInstance()
        val cityRealm = CityRealm(defaultCity)
        realm.executeTransaction { realm1 -> realm1.insertOrUpdate(cityRealm) }
        realm.close()
    }
}
