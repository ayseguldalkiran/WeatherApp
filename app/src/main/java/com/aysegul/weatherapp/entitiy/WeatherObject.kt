package com.aysegul.weatherapp.entitiy

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherObject (
    @SerializedName("main" )
    @Expose
    var main: String,
    @SerializedName("id" )
    @Expose
    var id: Int,
    @SerializedName("description")
    @Expose
    var description: String,
    @SerializedName("icon")
    @Expose
    var icon: String): Serializable {
}