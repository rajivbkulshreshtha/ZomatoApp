package com.example.zomatoapp.data.model.cities

import com.google.gson.annotations.SerializedName

data class CitiesResponse(

    @SerializedName("status")
    var staus: String,
    @field:SerializedName("has_more")
    var hasMore: Long = 0,

    @field:SerializedName("has_total")
    var hasTotal: Long = 0,

    @field:SerializedName("location_suggestions")
    var locationSuggestions: List<City>

)
