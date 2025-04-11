package com.clockscreensaver.android.logic.network

import android.util.Log
import com.clockscreensaver.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object ClockScreenSaverNetwork {
    private val placeService =ServiceCreator.create<PlaceService>("aMap")
    private val weatherService =ServiceCreator.create<WeatherService>()

//    suspend 关键字用于标记一个函数为挂起函数（suspend function）。挂起函数是一种特殊的函数，它可以在执行过程中暂停（suspend）并在稍后的时间点恢复执行。这种暂停不会阻塞线程，而是允许其他代码在函数暂停时执行
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()
    suspend fun getRealtimeWeather(lng: String, lat: String) = weatherService.getRealtimeWeather(lng, lat).await()
    suspend fun getDailyWeather(lng: String, lat: String, dailysteps: Number) = weatherService.getDailyWeather(lng, lat, dailysteps).await()

    private suspend fun <T> Call<T>.await(): T {
        return  suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

                override fun onFailure(p0: Call<T>, p1: Throwable) {
                    continuation.resumeWithException(p1)
                }
            })
        }
    }
}