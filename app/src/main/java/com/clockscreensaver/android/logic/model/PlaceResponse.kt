package com.clockscreensaver.android.logic.model

data class PlaceResponse(val status: String, val pois: List<Place>)

data class Place(val name: String, val location: String, val address: String)


