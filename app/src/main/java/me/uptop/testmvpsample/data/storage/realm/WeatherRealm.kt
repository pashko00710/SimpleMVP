package me.uptop.testmvpsample.data.storage.realm

import io.realm.RealmObject

open class WeatherRealm(var id: Int = 0,
                        var main: String? = null,
                        var description: String? = null,
                        var icon: String? = null) : RealmObject()
