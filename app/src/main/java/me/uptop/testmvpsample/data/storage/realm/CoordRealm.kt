package me.uptop.testmvpsample.data.storage.realm

import io.realm.RealmObject

open class CoordRealm(var lon: Float = 0.0f,
                      var lat: Float = 0.0f) : RealmObject()