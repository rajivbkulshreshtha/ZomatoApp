package com.example.zomatoapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "selected_city")
data class SelectedCity(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null, // Mumbai

    @ColumnInfo(name = "lat")
    @SerializedName("lat")
    var lat: String? = null, // 19.3012913455

    @ColumnInfo(name = "lon")
    @SerializedName("lon")
    var lon: String? = null // 72.8516744964
) : Parcelable