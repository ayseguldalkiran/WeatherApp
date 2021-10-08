package com.aysegul.weatherapp.entitiy

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Temp (
    @SerializedName("min" )
    @Expose
    var min: Double,
    @SerializedName("max" )
    @Expose
    var max: Double): Serializable {
}

