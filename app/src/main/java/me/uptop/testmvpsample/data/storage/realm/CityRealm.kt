package me.uptop.testmvpsample.data.storage.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CityRealm(@PrimaryKey var cityName: String? = null) : RealmObject()