package com.aysegul.weatherapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.aysegul.weatherapp.entitiy.WeatherEntitiy
import com.aysegul.weatherapp.retrofit.ApiUtils
import com.aysegul.weatherapp.retrofit.WeatherDaoInterface
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherDaoRepository {
    private val weatherList: MutableLiveData<WeatherEntitiy>
    private val wdaoi: WeatherDaoInterface
    private var apiKey: String

    init {
        wdaoi = ApiUtils.getWeatherDaoInterface()
        weatherList = MutableLiveData()
        apiKey = ""
    }

    fun getWeatherList(): MutableLiveData<WeatherEntitiy> {
        return weatherList
    }

    fun getWeather(lat : Double, lon: Double, exclude: String, appid: String){
        wdaoi.getWeatherInfo(lat, lon, exclude, appid).enqueue(object : Callback<WeatherEntitiy> {
            override fun onResponse(call: Call<WeatherEntitiy>, response: Response<WeatherEntitiy>) {
                val list = response.body()
                weatherList.value = list
            }
            override fun onFailure(call: Call<WeatherEntitiy>, t: Throwable?) {}
        })
    }

    fun setApiKey(text: String) {
        apiKey = text
    }

    fun getLocation(latitude: Double, longitude: Double) {
        wdaoi.getWeatherInfo(
            latitude,
            longitude,
            "minutely,hourly",
            "8ddadecc7ae4f56fee73b2b405a63659"
        ).enqueue(
            object : Callback<WeatherEntitiy> {
                override fun onResponse(
                    call: Call<WeatherEntitiy>?,
                    response: Response<WeatherEntitiy>
                ) {
                    val weather = response.body()
                    weatherList.value = weather
                }

                override fun onFailure(call: Call<WeatherEntitiy>?, t: Throwable?) {
                    Log.e("fail", t?.message.toString())
                }
            })
    }

    fun getApiKey(): String {
        return apiKey
    }
}