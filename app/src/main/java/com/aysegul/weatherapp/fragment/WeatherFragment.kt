package com.aysegul.weatherapp.fragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aysegul.weatherapp.databinding.FragmentWeatherBinding
import com.aysegul.weatherapp.retrofit.ApiUtils
import com.aysegul.weatherapp.retrofit.WeatherDaoInterface
import com.aysegul.weatherapp.viewModel.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var pdaoi: WeatherDaoInterface
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        pdaoi = ApiUtils.getWeatherDaoInterface()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        viewModel = WeatherViewModel()

        fetchLocation()

            viewModel.weatherList.observe(viewLifecycleOwner, { weather ->
                if(weather != null) {
                binding.textView.text = weather.timezone
                binding.textView2.text = weather.current.temp.toString()
                binding.textView3.text = findDayOfTheWeek(weather.daily[0].dt)
                binding.textView4.text = findDayOfTheWeek(weather.daily[1].dt)
                binding.textView5.text = findDayOfTheWeek(weather.daily[2].dt)
                binding.textView6.text = findDayOfTheWeek(weather.daily[3].dt)
                binding.textView7.text = findDayOfTheWeek(weather.daily[4].dt)
                binding.textView8.text = findDayOfTheWeek(weather.daily[5].dt)
                binding.textView10.text = findDayOfTheWeek(weather.daily[6].dt)

                binding.textView11.text = weather.daily[0].weather[0].main
                binding.textView12.text = weather.daily[1].weather[0].main
                binding.textView13.text = weather.daily[2].weather[0].main
                binding.textView14.text = weather.daily[3].weather[0].main
                binding.textView15.text = weather.daily[4].weather[0].main
                binding.textView16.text = weather.daily[5].weather[0].main
                binding.textView17.text = weather.daily[6].weather[0].main

                binding.textView18.text = weather.daily[0].temp.min.toString()
                binding.textView9.text = weather.daily[1].temp.min.toString()
                binding.textView19.text = weather.daily[2].temp.min.toString()
                binding.textView20.text = weather.daily[3].temp.min.toString()
                binding.textView21.text = weather.daily[4].temp.min.toString()
                binding.textView22.text = weather.daily[5].temp.min.toString()
                binding.textView23.text = weather.daily[6].temp.min.toString()

                binding.textView24.text = weather.daily[0].temp.max.toString()
                binding.textView25.text = weather.daily[1].temp.max.toString()
                binding.textView26.text = weather.daily[2].temp.max.toString()
                binding.textView27.text = weather.daily[3].temp.max.toString()
                binding.textView28.text = weather.daily[4].temp.max.toString()
                binding.textView29.text = weather.daily[5].temp.max.toString()
                binding.textView30.text = weather.daily[6].temp.max.toString()


                Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[0].weather[0].icon + "@4x.png").into(binding.imageView4)
                Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[1].weather[0].icon + "@4x.png").into(binding.imageView5)
                Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[2].weather[0].icon + "@4x.png").into(binding.imageView6)
                Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[3].weather[0].icon + "@4x.png").into(binding.imageView7)
                Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[4].weather[0].icon + "@4x.png").into(binding.imageView8)
                Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[5].weather[0].icon + "@4x.png").into(binding.imageView9)
                Picasso.get().load("http://openweathermap.org/img/wn/" + weather.daily[6].weather[0].icon + "@4x.png").into(binding.imageView10)
                }
                else {
                    Toast.makeText(context, "Lütfen geçerli bir API Key giriniz.", Toast.LENGTH_SHORT).show()
                }
            })

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
            Toast.makeText(context, "Lütfen konum izni veriniz.", Toast.LENGTH_SHORT).show()
            return
        }
        task.addOnSuccessListener {
            val bundle: WeatherFragmentArgs by navArgs()
            val incomingObject = bundle.sharedKey
            if(it != null) {
                viewModel.wdaor.getWeatherInfo(it.latitude, it.longitude, incomingObject)
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
        val d = SimpleDateFormat("EEEE")
        val date = SimpleDateFormat("dd-MM-yyyy").parse(a)
        val dayOfTheWeek: String = d.format(date)

        return dayOfTheWeek
    }
}