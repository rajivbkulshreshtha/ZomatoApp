package com.example.zomatoapp.data.model.restaurants

import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Parcelize
@Entity(tableName = "%s")
data class NearbyRestaurant(
    @ColumnInfo(name = "restaurant")
    @SerializedName("restaurant")
    var restaurant: Restaurant?
) : Parcelable