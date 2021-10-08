package com.aysegul.weatherapp.entitiy

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SharedKey(
    @SerializedName("sharedKey")
    @Expose
    var sharedKey: Double): Serializable {
}