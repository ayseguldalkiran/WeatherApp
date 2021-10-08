package com.aysegul.weatherapp.entitiy

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Daily(
    @SerializedName("dt" )
    @Expose
    var dt: Int,
    @SerializedName("temp")
    @Expose
    var temp: Temp): Serializable {
}