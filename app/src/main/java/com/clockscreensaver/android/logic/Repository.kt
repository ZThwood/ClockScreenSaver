package com.clockscreensaver.android.logic

import androidx.lifecycle.liveData
import com.clockscreensaver.android.logic.model.Place
import com.clockscreensaver.android.logic.network.ClockScreenSaverNetwork
import kotlinx.coroutines.Dispatchers

object Repository {
    // 自动构建并返回一个LiveDate对象。指定 Dispatchers.IO 说明要在子进程请求
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = ClockScreenSaverNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }

        } catch (e:Exception) {
            Result.failure<List<Place>>(e)
        }

        emit(result)
    }
}