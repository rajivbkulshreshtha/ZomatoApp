package com.example.zomatoapp.data.model.restaurants

import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Parcelize
@Entity(tableName = "%s")
data class HasMenuStatus(
    @ColumnInfo(name = "delivery")
    @SerializedName("delivery")
    var delivery: Int?, // 1
    @ColumnInfo(name = "takeaway")
    @SerializedName("takeaway")
    var takeaway: Int? // -1
) : Parcelable