package me.uptop.testmvpsample.data.network.response.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds {
    @SerializedName("all")
    @Expose
    var all: Int = 0
}
