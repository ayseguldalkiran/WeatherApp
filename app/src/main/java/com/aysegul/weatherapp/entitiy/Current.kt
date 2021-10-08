package com.aysegul.weatherapp.entitiy

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Current (
    @SerializedName("temp")
    @Expose
    var temp: Double): Serializable {
}


