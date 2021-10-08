package com.aysegul.weatherapp.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aysegul.weatherapp.databinding.FragmentHomeBinding
import com.aysegul.weatherapp.repo.WeatherDaoRepository


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val wdaor = WeatherDaoRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.button.setOnClickListener {
            val transition = HomeFragmentDirections.actionHomeFragmentToWeatherFragment()
            Navigation.findNavController(it).navigate(transition)
        }

        return binding.root

    }
}
