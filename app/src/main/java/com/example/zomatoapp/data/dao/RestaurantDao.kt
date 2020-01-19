package com.example.zomatoapp.data.dao

import androidx.room.*
import com.example.zomatoapp.data.model.restaurants.Restaurant
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(Restaurant: List<Restaurant>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Restaurant: Restaurant): Long

    @Delete
    fun delete(Restaurant: Restaurant): Int

    @Query("DELETE FROM Restaurant")
    fun deleteAll(): Int

    @Query("SELECT * FROM Restaurant")
    fun getAll(): Observable<List<Restaurant>>

    @Query("SELECT * FROM Restaurant LIMIT 1")
    fun get(): Single<Restaurant>

    @Query("SELECT COUNT(*) from Restaurant")
    fun count(): Single<Int>
}