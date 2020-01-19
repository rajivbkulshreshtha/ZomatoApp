package com.example.zomatoapp.data.model.restaurants

import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Parcelize
@Entity(tableName = "%s")
data class Popularity(
    @ColumnInfo(name = "city")
    @SerializedName("city")
    var city: String?, // Mumbai
    @ColumnInfo(name = "nearby_res")
    @SerializedName("nearby_res")
    var nearbyRes: List<String?>?,
    @ColumnInfo(name = "nightlife_index")
    @SerializedName("nightlife_index")
    var nightlifeIndex: String?, // 0.72
    @ColumnInfo(name = "nightlife_res")
    @SerializedName("nightlife_res")
    var nightlifeRes: String?, // 10
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    var popularity: String?, // 4.59
    @ColumnInfo(name = "popularity_res")
    @SerializedName("popularity_res")
    var popularityRes: String?, // 100
    @ColumnInfo(name = "subzone")
    @SerializedName("subzone")
    var subzone: String?, // Bhayandar
    @ColumnInfo(name = "subzone_id")
    @SerializedName("subzone_id")
    var subzoneId: Int?, // 2404
    @ColumnInfo(name = "top_cuisines")
    @SerializedName("top_cuisines")
    var topCuisines: List<String?>?
) : Parcelable