package com.example.zomatoapp.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zomatoapp.data.repo.AppRepository
import com.example.zomatoapp.helpers.plus
import com.example.zomatoapp.data.model.restaurants.Restaurant
import com.example.zomatoapp.view.base.BaseViewModel
import com.google.android.gms.location.LocationRequest
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider

class MainViewModel(private val repository: AppRepository) :
    BaseViewModel() {


    companion object {
        public const val TAG = "MainViewModel"

    }

    val restaurantLiveData = MutableLiveData<List<Restaurant>>()
    val addressLiveData = MutableLiveData<List<Address>>()


    fun nearbyRestaurants(lat: Double, lon: Double, isOnline: Boolean = true) {
        loadingDataLiveData.postValue(true)

        compositeDisposable + repository.nearbyRestaurants(isOnline, lat, lon)
            .subscribe({
                restaurantLiveData.postValue(it)
                loadingDataLiveData.postValue(false)
                Log.d(TAG, "nearbyRestaurants-success:$it ");
            }, {
                restaurantLiveData.postValue(null)
                loadingDataLiveData.postValue(false)
                it.printStackTrace()
                Log.d(TAG, "nearbyRestaurants-error:${it.localizedMessage} ");
            })


    }

    fun nearbyRestaurantsByGPS(context: Context) {
        loadingDataLiveData.postValue(true)

        compositeDisposable + getCurrentLocation(context)!!
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({

                nearbyRestaurants(it.latitude, it.longitude)

                Log.d(TAG, "getCurrentLocation-success:$it");
            }, {
                restaurantLiveData.postValue(null)
                loadingDataLiveData.postValue(false)

                it.printStackTrace()
                Log.d(TAG, "getCurrentLocation-error:${it.localizedMessage} ");
            })
    }

    fun findLatLonOfCity(context: Context, query: String) {
        val locationProvider = ReactiveLocationProvider(context)
        val geocodeObservable = locationProvider
            .getGeocodeObservable(query, 1)
        compositeDisposable + geocodeObservable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                addressLiveData.postValue(it)
                Log.d(TAG, "geocodeObservable-success:$it ");
            }, {
                addressLiveData.postValue(null)
                Log.d(TAG, "geocodeObservable-error:$it ");
            })
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(context: Context): Observable<Location>? {
        val request = LocationRequest.create() //standard GMS LocationRequest
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setNumUpdates(1).setInterval(20)

        val locationProvider = ReactiveLocationProvider(context)
        return locationProvider.getUpdatedLocation(request)
    }
}