package com.example.zomatoapp.data.model.restaurants

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Restaurant")
data class Restaurant(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: String, // 18674221
    @ColumnInfo(name = "apikey")
    @SerializedName("apikey")
    var apikey: String?, // f937f04657a90cecfb65063e2788e0a7
    @ColumnInfo(name = "average_cost_for_two")
    @SerializedName("average_cost_for_two")
    var averageCostForTwo: Int?, // 400
    @ColumnInfo(name = "book_again_url")
    @SerializedName("book_again_url")
    var bookAgainUrl: String?,
    @ColumnInfo(name = "book_form_web_view_url")
    @SerializedName("book_form_web_view_url")
    var bookFormWebViewUrl: String?,
    @ColumnInfo(name = "cuisines")
    @SerializedName("cuisines")
    var cuisines: String?, // Fast Food
    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    var currency: String?, // Rs.
    @ColumnInfo(name = "deeplink")
    @SerializedName("deeplink")
    var deeplink: String?, // zomato://restaurant/18674221
    @ColumnInfo(name = "events_url")
    @SerializedName("events_url")
    var eventsUrl: String?, // https://www.zomato.com/mumbai/chai-sutta-bar-bhayandar/events#tabtop?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1
    @ColumnInfo(name = "featured_image")
    @SerializedName("featured_image")
    var featuredImage: String?, // https://b.zmtcdn.com/data/reviews_photos/bf9/3285fdd0c5c458fe5692c785cfcdebf9_1542024470.jpg?output-format=webp
    @ColumnInfo(name = "has_online_delivery")
    @SerializedName("has_online_delivery")
    var hasOnlineDelivery: Int?, // 1
    @ColumnInfo(name = "has_table_booking")
    @SerializedName("has_table_booking")
    var hasTableBooking: Int?, // 0
    @ColumnInfo(name = "include_bogo_offers")
    @SerializedName("include_bogo_offers")
    var includeBogoOffers: Boolean?, // true
    @ColumnInfo(name = "is_book_form_web_view")
    @SerializedName("is_book_form_web_view")
    var isBookFormWebView: Int?, // 0
    @ColumnInfo(name = "is_delivering_now")
    @SerializedName("is_delivering_now")
    var isDeliveringNow: Int?, // 1
    @ColumnInfo(name = "is_table_reservation_supported")
    @SerializedName("is_table_reservation_supported")
    var isTableReservationSupported: Int?, // 0
    @ColumnInfo(name = "is_zomato_book_res")
    @SerializedName("is_zomato_book_res")
    var isZomatoBookRes: Int?, // 0
    @ColumnInfo(name = "menu_url")
    @SerializedName("menu_url")
    var menuUrl: String?, // https://www.zomato.com/mumbai/chai-sutta-bar-bhayandar/menu?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1&openSwipeBox=menu&showMinimal=1#tabtop
    @ColumnInfo(name = "mezzo_provider")
    @SerializedName("mezzo_provider")
    var mezzoProvider: String?, // OTHER
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String?, // Chai Sutta Bar
    /*@ColumnInfo(name = "offers")
    @SerializedName("offers")
    var offers: List<Any?>?,*/
    @ColumnInfo(name = "opentable_support")
    @SerializedName("opentable_support")
    var opentableSupport: Int?, // 0
    @ColumnInfo(name = "order_deeplink")
    @SerializedName("order_deeplink")
    var orderDeeplink: String?,
    @ColumnInfo(name = "order_url")
    @SerializedName("order_url")
    var orderUrl: String?, // https://www.zomato.com/mumbai/chai-sutta-bar-bhayandar/order?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1
    @ColumnInfo(name = "photos_url")
    @SerializedName("photos_url")
    var photosUrl: String?, // https://www.zomato.com/mumbai/chai-sutta-bar-bhayandar/photos?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1#tabtop
    @ColumnInfo(name = "price_range")
    @SerializedName("price_range")
    var priceRange: Int?, // 1
    @ColumnInfo(name = "switch_to_order_menu")
    @SerializedName("switch_to_order_menu")
    var switchToOrderMenu: Int?, // 0
    @ColumnInfo(name = "thumb")
    @SerializedName("thumb")
    var thumb: String?, // https://b.zmtcdn.com/data/reviews_photos/bf9/3285fdd0c5c458fe5692c785cfcdebf9_1542024470.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A
    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String?// https://www.zomato.com/mumbai/chai-sutta-bar-bhayandar?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1
) : Parcelable {

    @SerializedName("location")
    @Ignore
    var location: LocationX? = null


    @SerializedName("user_rating")
    @Ignore
    var userRating: UserRating? = null


    @SerializedName("R")
    @Ignore
    var r: R? = null

    override fun toString(): String {
        return "Restaurant(apikey=$apikey, averageCostForTwo=$averageCostForTwo, bookAgainUrl=$bookAgainUrl, bookFormWebViewUrl=$bookFormWebViewUrl, cuisines=$cuisines, currency=$currency, deeplink=$deeplink, eventsUrl=$eventsUrl, featuredImage=$featuredImage, hasOnlineDelivery=$hasOnlineDelivery, hasTableBooking=$hasTableBooking, id=$id, includeBogoOffers=$includeBogoOffers, isBookFormWebView=$isBookFormWebView, isDeliveringNow=$isDeliveringNow, isTableReservationSupported=$isTableReservationSupported, isZomatoBookRes=$isZomatoBookRes, menuUrl=$menuUrl, mezzoProvider=$mezzoProvider, name=$name, opentableSupport=$opentableSupport, orderDeeplink=$orderDeeplink, orderUrl=$orderUrl, photosUrl=$photosUrl, priceRange=$priceRange, switchToOrderMenu=$switchToOrderMenu, thumb=$thumb, url=$url, location=$location, userRating=$userRating, r=$r)"
    }


}