package com.example.koin.di

import android.app.Application
import com.example.zomatoapp.data.repo.AppRepository
import com.example.zomatoapp.viewmodel.CitySearchViewModel
import com.example.zomatoapp.viewmodel.MainViewModel
import com.google.gson.Gson
import me.ebraheem.restaurants.data.preferences.AppPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModel = module {


    single {
        createAppPreferences(androidApplication(), get())
    }

    single<AppRepository>(createdAtStart = true) {
        AppRepository(
            get(),
            get(),
            get()
        )
    }

    viewModel { MainViewModel(get()) }

    viewModel { CitySearchViewModel(get()) }

}

val zomatoApp = listOf(networkModule, localDatasourceModule, appModel)


fun createAppPreferences(application: Application, gson: Gson): AppPreferences {
    return AppPreferences(application, gson)
}