package com.example.zomatoapp.data.model.restaurants


import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Parcelize
@Entity(tableName = "%s")
data class LocationX(
    @ColumnInfo(name = "address")
    @SerializedName("address")
    var address: String?, // 150 Feet Road, Padmavati Nagar, Bhayandar West, Mira Bhayandar, Bhayandar
    @ColumnInfo(name = "city")
    @SerializedName("city")
    var city: String?, // Mumbai
    @ColumnInfo(name = "city_id")
    @SerializedName("city_id")
    var cityId: Int?, // 3
    @ColumnInfo(name = "country_id")
    @SerializedName("country_id")
    var countryId: Int?, // 1
    @ColumnInfo(name = "latitude")
    @SerializedName("latitude")
    var latitude: String?, // 19.2946093403
    @ColumnInfo(name = "locality")
    @SerializedName("locality")
    var locality: String?, // Bhayandar
    @ColumnInfo(name = "locality_verbose")
    @SerializedName("locality_verbose")
    var localityVerbose: String?, // Bhayandar, Mumbai
    @ColumnInfo(name = "longitude")
    @SerializedName("longitude")
    var longitude: String?, // 72.8475486115
    @ColumnInfo(name = "zipcode")
    @SerializedName("zipcode")
    var zipcode: String?
) : Parcelable