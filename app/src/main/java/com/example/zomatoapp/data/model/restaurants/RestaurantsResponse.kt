package com.example.zomatoapp.data.model.restaurants

import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Parcelize
@Entity(tableName = "%s")
data class RestaurantsResponse(
    @ColumnInfo(name = "link")
    @SerializedName("link")
    var link: String?, // https://www.zomato.com/mumbai/bhayandar-restaurants
    @ColumnInfo(name = "location")
    @SerializedName("location")
    var location: Location?,
    @ColumnInfo(name = "nearby_restaurants")
    @SerializedName("nearby_restaurants")
    var nearbyRestaurants: List<NearbyRestaurant?>?,
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    var popularity: Popularity?
) : Parcelable