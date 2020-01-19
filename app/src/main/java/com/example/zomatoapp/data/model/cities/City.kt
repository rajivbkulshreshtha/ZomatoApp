package com.example.zomatoapp.data.model.cities


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "city")
data class City(
    @ColumnInfo(name = "country_flag_url")
    @SerializedName("country_flag_url")
    var countryFlagUrl: String? = null, // https://b.zmtcdn.com/images/countries/flags/country_1.png
    @ColumnInfo(name = "country_id")
    @SerializedName("country_id")
    var countryId: Int? = null, // 1
    @ColumnInfo(name = "country_name")
    @SerializedName("country_name")
    var countryName: String? = null, // India
    @ColumnInfo(name = "discovery_enabled")
    @SerializedName("discovery_enabled")
    var discoveryEnabled: Int? = null, // 0
    @ColumnInfo(name = "has_go_out_tab")
    @SerializedName("has_go_out_tab")
    var hasGoOutTab: Int? = null, // 0
    @ColumnInfo(name = "has_new_ad_format")
    @SerializedName("has_new_ad_format")
    var hasNewAdFormat: Int? = null, // 1
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int? = null, // 11504
    @ColumnInfo(name = "is_state")
    @SerializedName("is_state")
    var isState: Int? = null, // 0
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null, // Muzaffarpur
    @ColumnInfo(name = "should_experiment_with")
    @SerializedName("should_experiment_with")
    var shouldExperimentWith: Int? = null, // 0
    @ColumnInfo(name = "state_code")
    @SerializedName("state_code")
    var stateCode: String? = null,
    @ColumnInfo(name = "state_id")
    @SerializedName("state_id")
    var stateId: Int? = null, // 0
    @ColumnInfo(name = "state_name")
    @SerializedName("state_name")
    var stateName: String? = null
) : Parcelable