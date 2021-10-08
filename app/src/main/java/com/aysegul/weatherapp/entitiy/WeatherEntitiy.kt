package com.aysegul.weatherapp.entitiy

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherEntitiy (
    @SerializedName("lat")
    @Expose
    var lat: Double,
    @SerializedName("lon")
    @Expose
    var lon: Double,
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

