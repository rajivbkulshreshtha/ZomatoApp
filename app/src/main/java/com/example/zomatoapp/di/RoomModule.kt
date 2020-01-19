package com.example.koin.di

import androidx.room.Room
import com.example.zomatoapp.data.db.AppDatabase
import org.koin.dsl.module

val localDatasourceModule = module {

    single { Room.databaseBuilder(get(), AppDatabase::class.java, "zomato_database").build() }

    single { get<AppDatabase>().restaurantDao() }

}