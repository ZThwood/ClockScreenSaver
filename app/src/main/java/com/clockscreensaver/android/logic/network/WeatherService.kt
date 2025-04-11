package com.clockscreensaver.android.logic.network


import com.clockscreensaver.android.logic.model.DailyResponse
import com.clockscreensaver.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("{lng},{lat}/realtime")
    fun getRealtimeWeather(@Path("lng") lng:String, @Path("lat") lat: String): Call<RealtimeResponse>

    @GET("{lng},{lat}/daily")
    fun getDailyWeather(@Path("lng") lng:String, @Path("lat") lat: String,  @Query("dailysteps") dailysteps: Number = 1): Call<DailyResponse>

}