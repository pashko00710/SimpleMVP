package me.uptop.testmvpsample.data.network.response.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Wind {
    @SerializedName("speed")
    @Expose
    var speed: Float = 0.toFloat()
    @SerializedName("deg")
    @Expose
    var deg: Int = 0
}
