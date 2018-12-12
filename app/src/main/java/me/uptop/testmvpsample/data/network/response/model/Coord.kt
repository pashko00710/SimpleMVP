package me.uptop.testmvpsample.data.network.response.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coord {
    @SerializedName("lon")
    @Expose
    var lon: Float = 0.toFloat()
    @SerializedName("lat")
    @Expose
    var lat: Float = 0.toFloat()
}
