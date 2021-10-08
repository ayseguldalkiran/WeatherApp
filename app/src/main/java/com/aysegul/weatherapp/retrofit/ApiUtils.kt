package com.aysegul.weatherapp.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun getWeatherDaoInterface(): WeatherDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(WeatherDaoInterface::class.java)
        }
    }
}