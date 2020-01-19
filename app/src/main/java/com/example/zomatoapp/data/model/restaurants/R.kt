package com.example.zomatoapp.data.model.restaurants

import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Parcelize
@Entity(tableName = "%s")
data class R(
    @ColumnInfo(name = "has_menu_status")
    @SerializedName("has_menu_status")
    var hasMenuStatus: HasMenuStatus?,
    @ColumnInfo(name = "res_id")
    @SerializedName("res_id")
    var resId: Int? // 18674221
) : Parcelable