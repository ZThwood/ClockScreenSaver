package com.clockscreensaver.android.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.clockscreensaver.android.logic.Repository
import com.clockscreensaver.android.logic.model.Place



class PlaceViewModel: ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    val placeLiveData: LiveData<Result<List<Place>>> =
        searchLiveData.switchMap { query ->
            Repository.searchPlaces(query)
        }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}