package me.uptop.testmvpsample.data.storage.realm

import io.realm.RealmObject

open class MainRealm(var temp: Float = 0.0f,
                     var pressure: Int = 0,
                     var humidity: Int = 0,
                     var tempMin: Float = 0.0f,
                     var tempMax: Float = 0.0f) : RealmObject()
