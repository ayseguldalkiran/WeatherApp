package com.aysegul.weatherapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.aysegul.weatherapp.entitiy.WeatherEntitiy
import com.aysegul.weatherapp.fragment.WeatherFragmentArgs
import com.aysegul.weatherapp.retrofit.ApiUtils
import com.aysegul.weatherapp.retrofit.WeatherDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherDaoRepository {
    private val weatherList: MutableLiveData<WeatherEntitiy>
    private val wdaoi: WeatherDaoInterface

    init {
        wdaoi = ApiUtils.getWeatherDaoInterface()
        weatherList = MutableLiveData()
    }

    fun getWeatherList(): MutableLiveData<WeatherEntitiy> {
        return weatherList
    }

    fun getWeatherInfo(latitude: Double, longitude: Double, appId: String) {
        wdaoi.getWeatherInfo(
            latitude,
            longitude,
            "minutely,hourly",
            appId
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
}