package com.clockscreensaver.android.logic.network

import com.clockscreensaver.android.ClockScreenSaverApplication
import com.clockscreensaver.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    // 当调用 searchPlaces 时，Retrofit 会自动发起 get 请求
    @GET("/place?lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}