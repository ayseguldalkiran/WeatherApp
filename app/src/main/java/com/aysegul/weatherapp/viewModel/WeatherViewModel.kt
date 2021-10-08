package com.aysegul.weatherapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aysegul.weatherapp.entitiy.WeatherEntitiy
import com.aysegul.weatherapp.repo.WeatherDaoRepository

class WeatherViewModel: ViewModel() {
    var weatherList = MutableLiveData<WeatherEntitiy>()
    val wdaor = WeatherDaoRepository()

    init {
        weatherList = wdaor.getWeatherList()
    }

    fun uploadWeather(lat : Double, lon: Double, exclude: String, appid: String) {
        wdaor.getWeather(lat, lon, exclude, appid)
    }
}