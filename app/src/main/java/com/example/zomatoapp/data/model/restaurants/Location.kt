package com.example.zomatoapp.data.model.restaurants

import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Parcelize
@Entity(tableName = "%s")
data class Location(
    @ColumnInfo(name = "city_id")
    @SerializedName("city_id")
    var cityId: Int?, // 3
    @ColumnInfo(name = "city_name")
    @SerializedName("city_name")
    var cityName: String?, // Mumbai
    @ColumnInfo(name = "country_id")
    @SerializedName("country_id")
    var countryId: Int?, // 1
    @ColumnInfo(name = "country_name")
    @SerializedName("country_name")
    var countryName: String?, // India
    @ColumnInfo(name = "entity_id")
    @SerializedName("entity_id")
    var entityId: Int?, // 2404
    @ColumnInfo(name = "entity_type")
    @SerializedName("entity_type")
    var entityType: String?, // subzone
    @ColumnInfo(name = "latitude")
    @SerializedName("latitude")
    var latitude: String?, // 19.3012913455
    @ColumnInfo(name = "longitude")
    @SerializedName("longitude")
    var longitude: String?, // 72.8516744964
    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? // Bhayandar
) : Parcelable