package com.aysegul.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import com.aysegul.weatherapp.entitiy.WeatherEntitiy
import com.aysegul.weatherapp.fragment.HomeFragment
import com.aysegul.weatherapp.retrofit.ApiUtils
import com.aysegul.weatherapp.retrofit.WeatherDaoInterface
import com.google.android.gms.location.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // getSupportFragmentManager()?.beginTransaction()?.add(android.R.id.content, HomeFragment()).commit();

    }

}