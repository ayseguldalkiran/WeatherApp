package com.aysegul.weatherapp.entitiy

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherEntitiy (
    @SerializedName("timezone")
    @Expose
    var timezone: String,
    @SerializedName("daily")
    @Expose
    var daily: List<Daily>,
    @SerializedName("current")
    @Expose
    var current: Current): Serializable {
}

