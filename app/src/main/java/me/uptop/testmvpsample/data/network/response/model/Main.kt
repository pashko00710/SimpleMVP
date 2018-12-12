package me.uptop.testmvpsample.data.network.response.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Main {
    @SerializedName("temp")
    @Expose
    var temp: Float = 0.toFloat()
    @SerializedName("pressure")
    @Expose
    var pressure: Int = 0
    @SerializedName("humidity")
    @Expose
    var humidity: Int = 0
    @SerializedName("temp_min")
    @Expose
    var tempMin: Float = 0.toFloat()
    @SerializedName("temp_max")
    @Expose
    var tempMax: Float = 0.toFloat()
}
