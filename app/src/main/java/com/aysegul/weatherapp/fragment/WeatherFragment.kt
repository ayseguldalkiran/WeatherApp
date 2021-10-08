package com.aysegul.weatherapp.fragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aysegul.weatherapp.databinding.FragmentWeatherBinding
import com.aysegul.weatherapp.entitiy.WeatherEntitiy
import com.aysegul.weatherapp.repo.WeatherDaoRepository
import com.aysegul.weatherapp.retrofit.ApiUtils
import com.aysegul.weatherapp.retrofit.WeatherDaoInterface
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var pdaoi: WeatherDaoInterface
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    val wdaor = WeatherDaoRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        pdaoi = ApiUtils.getWeatherDaoInterface()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

        fetchLocation()
        return binding.root
    }

    fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "Lütfen konum iznini veriniz.", Toast.LENGTH_SHORT).show()
            return
        }
        task.addOnSuccessListener {
            if(it != null) {
                getWeatherInfo(it.latitude, it.longitude)
            }
        }
    }

    fun findDayOfTheWeek(dt: Int) : String {
        val a =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                java.time.format.DateTimeFormatter.ISO_INSTANT
                    .format(java.time.Instant.ofEpochSecond(dt.toLong()))
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        val sdf = SimpleDateFormat("EEEE")
        val date = SimpleDateFormat("dd-MM-yyyy").parse(a)

        val dayOfTheWeek: String = sdf.format(date)

        return dayOfTheWeek
    }


    fun getWeatherInfo(latitude: Double, longitude: Double) {
        val bundle: WeatherFragmentArgs by navArgs()
        val incomingObject = bundle.sharedKey

        pdaoi.getWeatherInfo(
            latitude,
            longitude,
            "minutely,hourly",
          incomingObject
        ).enqueue(
            object : Callback<WeatherEntitiy> {
                override fun onResponse(
                    call: Call<WeatherEntitiy>?,
                    response: Response<WeatherEntitiy>
                ) {
                    val weather = response.body()

                    binding.textView.text = weather.timezone
                    binding.textView2.text = weather.current.temp.toString()
                    binding.textView3.text = findDayOfTheWeek(weather.daily[0].dt)
                    binding.textView4.text = findDayOfTheWeek(weather.daily[1].dt)
                    binding.textView5.text = findDayOfTheWeek(weather.daily[2].dt)
                    binding.textView6.text = findDayOfTheWeek(weather.daily[3].dt)


                    Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[0].weather[0].icon + "@4x.png").into(binding.imageView4)
                    Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[1].weather[0].icon + "@4x.png").into(binding.imageView5)
                    Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[2].weather[0].icon + "@4x.png").into(binding.imageView6)
                    Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[3].weather[0].icon + "@4x.png").into(binding.imageView7)
                    Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[4].weather[0].icon + "@4x.png").into(binding.imageView8)
                    Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[5].weather[0].icon + "@4x.png").into(binding.imageView9)
                    Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[6].weather[0].icon + "@4x.png").into(binding.imageView10)



                    Log.e("**********", "*********")
                    Log.e("lat", weather.daily[0].dt.toString())
                    Log.e("lon", weather.lon.toString())
                    Log.e("dt", weather.current.dt.toString())
                    Log.e("timezone", weather.timezone)
                    Log.e("url", weather.current.weather[0].icon)

                }

                override fun onFailure(call: Call<WeatherEntitiy>?, t: Throwable?) {
                    Log.e("fail", t?.message.toString())
                }
            })
    }

}