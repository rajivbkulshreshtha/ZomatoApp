package com.example.zomatoapp.data.repo

import com.example.zomatoapp.data.network.Routes
import com.example.zomatoapp.data.dao.RestaurantDao
import com.example.zomatoapp.data.model.SelectedCity
import com.example.zomatoapp.data.model.cities.City
import com.example.zomatoapp.data.model.restaurants.Restaurant
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.ebraheem.restaurants.data.preferences.AppPreferences

class AppRepository(
    private val routes: Routes,
    private val restaurantDao: RestaurantDao,
    private val appPreferences: AppPreferences
) :
    DataRepository {

    companion object {
        public const val TAG = "AppRepository"
    }


    private var searchCityMap: MutableMap<String, List<City>> = mutableMapOf()

    override fun searchCity(query: String): Observable<List<City>> {
        searchCityMap[query]?.let {
            return Observable.just(it)
        }
        return routes.cities(query, 10)
            .subscribeOn(Schedulers.io())
            .flatMap {
                Observable.just(it.locationSuggestions)
            }
            .map {

                searchCityMap[query] = it
                it
            }
            .observeOn(AndroidSchedulers.mainThread())

    }

    override fun nearbyRestaurants(
        isOnline: Boolean,
        lat: Double,
        lon: Double
    ): Observable<List<Restaurant>> {

        return if (isOnline) {
            nearbyRestaurantsOnline(lat, lon)
        } else {
            nearbyRestaurantsOffline()
        }
    }


    private fun nearbyRestaurantsOnline(lat: Double, lon: Double): Observable<List<Restaurant>> {
        var restaurants: List<Restaurant>? = null
        return routes.geocode(lat, lon)
            .subscribeOn(Schedulers.io())
            .map {

                val selectedCity = SelectedCity(
                    it.location?.cityName,
                    it.location?.latitude,
                    it.location?.longitude
                )

                appPreferences.putSelectedCity(selectedCity)
                restaurants =
                    it.nearbyRestaurants?.map { it?.restaurant }?.filterNotNull() ?: mutableListOf()
            }.flatMapSingle {
                restaurantDao.insertAll(restaurants!!)
            }
            .flatMap {
                Observable.just(restaurants!!)
            }
            .observeOn(Schedulers.io())
    }

    private fun nearbyRestaurantsOffline(): Observable<List<Restaurant>> {
        return restaurantDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }


}