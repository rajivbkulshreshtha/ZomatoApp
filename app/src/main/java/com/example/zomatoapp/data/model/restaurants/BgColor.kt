package com.example.zomatoapp.data.model.restaurants

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "%s")
data class BgColor(
    @ColumnInfo(name = "tint")
    @SerializedName("tint")
    var tint: String?, // 600
    @ColumnInfo(name = "type")
    @SerializedName("type")
    var type: String? // lime
) : Parcelable