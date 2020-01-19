package com.example.zomatoapp.data.model.restaurants

import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Parcelize
@Entity(tableName = "%s")
data class RatingObj(
    @ColumnInfo(name = "bg_color")
    @SerializedName("bg_color")
    var bgColor: BgColor?,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: Title?
) : Parcelable