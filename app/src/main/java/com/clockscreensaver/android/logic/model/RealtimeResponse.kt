package com.clockscreensaver.android.logic.model

import com.google.gson.annotations.SerializedName

data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)
    data class Realtime(val temperature: Double, val skycon: String, @SerializedName("air_quality") val airQuality: AirQuality)
    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}
