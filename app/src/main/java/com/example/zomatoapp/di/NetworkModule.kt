package com.example.koin.di

import com.example.zomatoapp.data.network.Routes
import com.example.zomatoapp.data.network.AuthInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.ebraheem.restaurants.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {


    single { createGson() }

    single { createAuthInterceptor() }

    single { createOkHttpClient(get()) }

    single {
        createWebService<Routes>(
            get(),
            get(),
            Constants.API_BASE_URL
        )
    }
}


fun createAuthInterceptor(): AuthInterceptor {
    return AuthInterceptor()
}

fun createOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json; charset=utf-8")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
        .addInterceptor(authInterceptor)
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()
}

fun createGson(): Gson {
    return GsonBuilder()
        .create()
}


inline fun <reified T> createWebService(okHttpClient: OkHttpClient, gson: Gson, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}