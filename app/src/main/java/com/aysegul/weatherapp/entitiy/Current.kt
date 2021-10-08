package com.aysegul.weatherapp.entitiy

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Current (
    @SerializedName("dt" )
    @Expose
    var dt: Int,
    @SerializedName("temp")
    @Expose
    var temp: Double,
    @SerializedName("weather")
    @Expose
    var weather: List<WeatherObject>): Serializable {
}


