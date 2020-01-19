package com.example.zomatoapp.data.network

import com.example.zomatoapp.data.model.cities.CitiesResponse
import com.example.zomatoapp.data.model.restaurants.RestaurantsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Routes {
    @GET("/api/v2.1/cities")
    fun cities(@Query("q") q: String, @Query("count") count: Int): Observable<CitiesResponse>

    @GET("/api/v2.1/geocode")
    fun geocode(@Query("lat") lat: Double, @Query("lon") lon: Double): Observable<RestaurantsResponse>

}