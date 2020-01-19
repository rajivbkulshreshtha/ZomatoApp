package com.example.zomatoapp.data.repo

import com.example.zomatoapp.data.model.cities.City
import com.example.zomatoapp.data.model.restaurants.Restaurant
import io.reactivex.Observable

interface DataRepository {

    fun searchCity(query: String): Observable<List<City>>

    fun nearbyRestaurants(
        isOnline: Boolean = true,
        lat: Double,
        lon: Double
    ): Observable<List<Restaurant>>


}