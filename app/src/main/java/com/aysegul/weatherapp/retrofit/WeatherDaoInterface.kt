package com.aysegul.weatherapp.retrofit

import com.aysegul.weatherapp.entitiy.WeatherEntitiy
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDaoInterface {
    @GET("onecall")
    fun getWeatherInfo(@Query("lat") lat: Double,
                       @Query("lon") lon: Double,
                       @Query("exclude") exclude: String,
                       @Query("appid") appid: String) : Call<WeatherEntitiy>

}
