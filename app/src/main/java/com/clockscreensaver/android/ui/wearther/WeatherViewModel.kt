package com.clockscreensaver.android.ui.wearther


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.clockscreensaver.android.logic.Repository

class WeatherViewModel:ViewModel() {
    private val locationLiveData = MutableLiveData<String>()
    var locationLng = ""
    var locationLat = ""
    var placeName = ""
    var weatherLiveData = locationLiveData.switchMap { location ->
        val result = location.toString().split(",")
        Repository.refreshWeather(
            result[0], result[1],
            1
        )
    }

    fun refreshWeather(location: String) {
        locationLiveData.value = location
    }

}