package com.sirius.cybird.net.response

import com.google.gson.annotations.SerializedName

data class FilmDetailData(
    @SerializedName("rating") val rating: Rating,
    @SerializedName("reviews_count") val reviewsCount: Int,
    @SerializedName("wish_count") val wishCount: Int,
    @SerializedName("douban_site") val doubanSite: String,
    @SerializedName("year") val year: String,
    @SerializedName("images") val images: Images,
    @SerializedName("alt") val alt: String,
    @SerializedName("id") val id: String,
    @SerializedName("mobile_url") val mobileUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("do_count") val doCount: Any,
    @SerializedName("share_url") val shareUrl: String,
    @SerializedName("seasons_count") val seasonsCount: Any,
    @SerializedName("schedule_url") val scheduleUrl: String,
    @SerializedName("episodes_count") val episodesCount: Any,
    @SerializedName("countries") val countries: List<String>,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("collect_count") val collectCount: Int,
    @SerializedName("casts") val casts: List<Cast>,
    @SerializedName("current_season") val currentSeason: Any,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("subtype") val subtype: String,
    @SerializedName("directors") val directors: List<Director>,
    @SerializedName("comments_count") val commentsCount: Int,
    @SerializedName("ratings_count") val ratingsCount: Int,
    @SerializedName("aka") val aka: List<String>
)