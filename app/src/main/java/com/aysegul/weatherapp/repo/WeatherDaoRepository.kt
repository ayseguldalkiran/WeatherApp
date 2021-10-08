package com.aysegul.weatherapp.repo

import androidx.lifecycle.MutableLiveData
import com.aysegul.weatherapp.entitiy.WeatherEntitiy
import com.aysegul.weatherapp.retrofit.ApiUtils
import com.aysegul.weatherapp.retrofit.WeatherDaoInterface
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

    fun getApiKey(): String {
        return apiKey
    }
}