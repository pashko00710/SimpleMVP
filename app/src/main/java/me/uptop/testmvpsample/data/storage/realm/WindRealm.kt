package me.uptop.testmvpsample.data.storage.realm

import io.realm.RealmObject

open class WindRealm(var speed: Float = 0.0f,
                     var deg: Int = 0) : RealmObject()
