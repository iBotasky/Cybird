package com.sirius.cybird.net.response

import com.google.gson.annotations.SerializedName


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


        data class Content(
                @SerializedName("id") val id: String,
                @SerializedName("category") val category: String,
                @SerializedName("display_category") val displayCategory: String,
                @SerializedName("item_id") val itemId: String,
                @SerializedName("title") val title: String,
                @SerializedName("forward") val forward: String,
                @SerializedName("img_url") val imgUrl: String,
                @SerializedName("like_count") val likeCount: Int,
                @SerializedName("post_date") val postDate: String,
                @SerializedName("last_update_date") val lastUpdateDate: String,
                @SerializedName("author") val author: Author,
                @SerializedName("video_url") val videoUrl: String,
                @SerializedName("audio_url") val audioUrl: String,
                @SerializedName("audio_platform") val audioPlatform: Int,
                @SerializedName("start_video") val startVideo: String,
                @SerializedName("has_reading") val hasReading: Int,
                @SerializedName("volume") val volume: String,
                @SerializedName("pic_info") val picInfo: String,
                @SerializedName("words_info") val wordsInfo: String,
                @SerializedName("subtitle") val subtitle: String,
                @SerializedName("number") val number: Int,
                @SerializedName("serial_id") val serialId: Int,
                @SerializedName("serial_list") val serialList: List<Any>,
                @SerializedName("movie_story_id") val movieStoryId: String,
                @SerializedName("ad_id") val adId: Int,
                @SerializedName("ad_type") val adType: Int,
                @SerializedName("ad_pvurl") val adPvurl: String,
                @SerializedName("ad_linkurl") val adLinkurl: String,
                @SerializedName("ad_makettime") val adMakettime: String,
                @SerializedName("ad_closetime") val adClosetime: String,
                @SerializedName("ad_share_cnt") val adShareCnt: String,
                @SerializedName("ad_pvurl_vendor") val adPvurlVendor: String,
                @SerializedName("content_id") val contentId: String,
                @SerializedName("content_type") val contentType: String,
                @SerializedName("content_bgcolor") val contentBgcolor: String,
                @SerializedName("share_url") val shareUrl: String,
                @SerializedName("share_info") val shareInfo: ShareInfo,
                @SerializedName("share_list") val shareList: ShareList,
                @SerializedName("tag_list") val tagList: List<Any>
        ) {

            data class ShareInfo(
                    @SerializedName("url") val url: String,
                    @SerializedName("image") val image: String,
                    @SerializedName("title") val title: String,
                    @SerializedName("content") val content: String
            )


            data class Author(
                    @SerializedName("user_id") val userId: String,
                    @SerializedName("user_name") val userName: String,
                    @SerializedName("web_url") val webUrl: String,
                    @SerializedName("summary") val summary: String,
                    @SerializedName("desc") val desc: String,
                    @SerializedName("is_settled") val isSettled: String,
                    @SerializedName("settled_type") val settledType: String,
                    @SerializedName("fans_total") val fansTotal: String,
                    @SerializedName("wb_name") val wbName: String
            )


            data class ShareList(
                    @SerializedName("wx") val wx: Wx,
                    @SerializedName("wx_timeline") val wxTimeline: WxTimeline,
                    @SerializedName("weibo") val weibo: Weibo,
                    @SerializedName("qq") val qq: Qq
            ) {

                data class Qq(
                        @SerializedName("title") val title: String,
                        @SerializedName("desc") val desc: String,
                        @SerializedName("link") val link: String,
                        @SerializedName("imgUrl") val imgUrl: String,
                        @SerializedName("audio") val audio: String
                )


                data class Wx(
                        @SerializedName("title") val title: String,
                        @SerializedName("desc") val desc: String,
                        @SerializedName("link") val link: String,
                        @SerializedName("imgUrl") val imgUrl: String,
                        @SerializedName("audio") val audio: String
                )


                data class WxTimeline(
                        @SerializedName("title") val title: String,
                        @SerializedName("desc") val desc: String,
                        @SerializedName("link") val link: String,
                        @SerializedName("imgUrl") val imgUrl: String,
                        @SerializedName("audio") val audio: String
                )


                data class Weibo(
                        @SerializedName("title") val title: String,
                        @SerializedName("desc") val desc: String,
                        @SerializedName("link") val link: String,
                        @SerializedName("imgUrl") val imgUrl: String,
                        @SerializedName("audio") val audio: String
                )
            }
        }
    }
}