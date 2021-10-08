package com.aysegul.weatherapp.fragment

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aysegul.weatherapp.databinding.FragmentHomeBinding
import com.aysegul.weatherapp.entitiy.SharedKey
import com.aysegul.weatherapp.entitiy.WeatherEntitiy
import com.aysegul.weatherapp.repo.WeatherDaoRepository
import com.aysegul.weatherapp.retrofit.WeatherDaoInterface
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val wdaor = WeatherDaoRepository()
    private lateinit  var weatherInfo: ArrayList<WeatherEntitiy>
    private lateinit var pdaoi: WeatherDaoInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            val apiKey = binding.editTextApiKey.getText().toString()
            val transition = HomeFragmentDirections.actionHomeFragmentToWeatherFragment(apiKey)
            Navigation.findNavController(it).navigate(transition)
        }

        return binding.root

    }
}
