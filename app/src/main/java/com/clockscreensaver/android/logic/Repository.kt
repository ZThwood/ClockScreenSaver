package com.clockscreensaver.android.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.clockscreensaver.android.ClockScreenSaverApplication
import com.clockscreensaver.android.logic.model.Place
import com.clockscreensaver.android.logic.model.Weather
import com.clockscreensaver.android.logic.network.ClockScreenSaverNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

object Repository {
    // 自动构建并返回一个LiveDate对象。指定 Dispatchers.IO 说明要在子进程请求
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("THWOOD", "searchPlaces query: " + query + ClockScreenSaverApplication.WEATHER_TOKEN)

            val placeResponse = ClockScreenSaverNetwork.searchPlaces(query)
            if (placeResponse.status == "1") {
                val pois = placeResponse.pois
                Result.success(pois)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }

        } catch (e:Exception) {
            Result.failure<List<Place>>(e)
        }

        emit(result)
    }

    fun refreshWeather(lng: String, lat: String, dailysteps: Number) = liveData(Dispatchers.IO) {
        Log.i("refreshWeather", "refreshWeather: " + lng + " |||||" +lat )
        val result = try {
            coroutineScope {
                val deferredRealtime = async {
                    ClockScreenSaverNetwork.getRealtimeWeather(lng, lat)
                }

                val deferredDaily = async {
                    ClockScreenSaverNetwork.getDailyWeather(lng, lat, dailysteps)
                }

                val realtimeRes = deferredRealtime.await()
                val dailyRes = deferredDaily.await()


                if (realtimeRes.status == "ok" && dailyRes.status == "ok") {
                    val weather = Weather(realtimeRes.result.realtime, dailyRes.result.daily)
                    Result.success(weather)
                } else {
                    Result.failure(RuntimeException("response realtimeRes is ${realtimeRes.status} and dailyRes is ${dailyRes.status}"))
                }


            }
        } catch (e:Exception) {
            Result.failure<List<Place>>(e)
        }

        emit(result)
    }
}