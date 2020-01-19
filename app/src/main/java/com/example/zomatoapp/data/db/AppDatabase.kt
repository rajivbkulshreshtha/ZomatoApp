package com.example.zomatoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zomatoapp.data.dao.RestaurantDao
import com.example.zomatoapp.data.model.restaurants.Restaurant

@Database(entities = [Restaurant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

}