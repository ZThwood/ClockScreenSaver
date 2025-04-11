package com.clockscreensaver.android.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.clockscreensaver.android.ClockScreenSaverApplication
import com.clockscreensaver.android.logic.model.Place
import com.clockscreensaver.android.logic.network.ClockScreenSaverNetwork
import kotlinx.coroutines.Dispatchers

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
}