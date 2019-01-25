package com.sirius.cybird.net.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class OneDetailData(
        @SerializedName("res") val res: Int,
        @SerializedName("data") val data: Data
) {

    data class Data(
            @SerializedName("id") val id: String,
            @SerializedName("weather") val weather: Weather,
            @SerializedName("date") val date: String,
            @SerializedName("content_list") val contentList: List<Content>
    ) {

        data class Weather(
                @SerializedName("city_name") val cityName: String,
                @SerializedName("date") val date: String,
                @SerializedName("temperature") val temperature: String,
                @SerializedName("humidity") val humidity: String,
                @SerializedName("climate") val climate: String,
                @SerializedName("wind_direction") val windDirection: String,
                @SerializedName("hurricane") val hurricane: String,
                @SerializedName("icons") val icons: Icons
        ) {

            data class Icons(
                    @SerializedName("day") val day: String,
                    @SerializedName("night") val night: String
            )
        }

        @Parcelize
        data class Content(
                @SerializedName("id") val id: String,
                @SerializedName("display_category") val displayCategory: String,
                @SerializedName("title") val title: String,
                @SerializedName("forward") val forward: String,
                @SerializedName("img_url") val imgUrl: String,
                @SerializedName("like_count") val likeCount: Int,
                @SerializedName("post_date") val postDate: String,
                @SerializedName("last_update_date") val lastUpdateDate: String,
                @SerializedName("volume") val volume: String,
                @SerializedName("pic_info") val picInfo: String,
                @SerializedName("words_info") val wordsInfo: String
        ) : Parcelable

    }
}