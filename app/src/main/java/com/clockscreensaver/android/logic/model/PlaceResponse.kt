package com.clockscreensaver.android.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status: String, val places: List<Place>)

// API 返回的 JSON 字段是 formatted_address，但你想用 Kotlin 的 address 时要用 SerializedName 注解来映射
data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)


data class Location(val lng: String, val lat: String)
