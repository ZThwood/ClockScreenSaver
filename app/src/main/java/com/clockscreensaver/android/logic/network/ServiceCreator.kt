package com.clockscreensaver.android.logic.network

import android.util.Log
import com.clockscreensaver.android.ClockScreenSaverApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ServiceCreator {
    private  const val BASE_WEATHER_URL = "https://api.caiyunapp.com/v2.6/${ClockScreenSaverApplication.WEATHER_TOKEN}/"
    private const val BASE_A_MAP_URL= "https://restapi.amap.com/v3/"
    private  val aMapRetrofit = Retrofit.Builder().baseUrl(BASE_A_MAP_URL).addConverterFactory(
        GsonConverterFactory.create()).build()
    private  val weatherRetrofit = Retrofit.Builder().baseUrl(BASE_WEATHER_URL).addConverterFactory(
        GsonConverterFactory.create()).build()

    fun <T> create(serviceClass: Class<T>, type: String): T {
        Log.d("THWOOD", "searchPlaces BASE_WEATHER_URL: " + type )
        if (type == "aMap") {
            return  aMapRetrofit.create(serviceClass)
        }

        if (type == "weather") {
            return weatherRetrofit.create(serviceClass)
        }

        return weatherRetrofit.create(serviceClass)
    }
    inline fun <reified T> create(type: String = "weather"): T = create(T::class.java, type)
}