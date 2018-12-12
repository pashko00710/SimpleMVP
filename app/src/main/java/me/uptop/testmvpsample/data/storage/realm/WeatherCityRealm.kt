package me.uptop.testmvpsample.data.storage.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class WeatherCityRealm(@PrimaryKey var id: Long = 0,
                            var name: String? = null,
                            var coordRealm: CoordRealm? = null,
                            var weather: RealmList<WeatherRealm>? = null,
                            var base: String? = null,
                            var main: MainRealm? = null,
                            var visibility: Int = 0,
                            var wind: WindRealm? = null,
                            var clouds: CloudsRealm? = null,
                            var cod: Int = 0) : RealmObject()