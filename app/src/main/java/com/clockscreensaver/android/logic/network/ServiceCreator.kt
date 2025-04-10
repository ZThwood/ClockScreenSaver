package com.clockscreensaver.android.logic.network

import com.clockscreensaver.android.ClockScreenSaverApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ServiceCreator {
    private  const val BASE_WEATHER_URL = "https://api.caiyunapp.com/v2.6/${ClockScreenSaverApplication.WEATHER_TOKEN}/"
    private  val retrofit = Retrofit.Builder().baseUrl(BASE_WEATHER_URL).addConverterFactory(
        GsonConverterFactory.create()).build()
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}