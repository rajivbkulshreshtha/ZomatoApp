package com.example.zomatoapp.data.model.restaurants

import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Parcelize
@Entity(tableName = "%s")
data class UserRating(
    @ColumnInfo(name = "aggregate_rating")
    @SerializedName("aggregate_rating")
    var aggregateRating: String?, // 4.0
    @ColumnInfo(name = "rating_color")
    @SerializedName("rating_color")
    var ratingColor: String?, // 5BA829
    @ColumnInfo(name = "rating_obj")
    @SerializedName("rating_obj")
    var ratingObj: RatingObj?,
    @ColumnInfo(name = "rating_text")
    @SerializedName("rating_text")
    var ratingText: String?, // Very Good
    @ColumnInfo(name = "votes")
    @SerializedName("votes")
    var votes: String? // 246
) : Parcelable