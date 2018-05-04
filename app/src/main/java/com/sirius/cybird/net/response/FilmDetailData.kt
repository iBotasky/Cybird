package com.sirius.cybird.net.response

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.google.gson.annotations.SerializedName
import com.sirius.cybird.ui.movie.detail.MovieDetailAdapter

/**
 * Property	Description	Type	Basic	Advance	Premium	Default
id	条目id	str	Y	Y	Y	-
title	中文名	str	Y	Y	Y	-
original_title	原名	str	Y	Y	Y	''
aka	又名	array	Y	Y	Y	[]
alt	条目页URL	str	Y	Y	Y	-
mobile_url	移动版条目页URL	str	Y	Y	Y	-
rating	评分，见附录	dict	Y	Y	Y	-
ratings_count	评分人数	int	Y	Y	Y	0
wish_count	想看人数	int	Y	Y	Y	0
collect_count	看过人数	int	Y	Y	Y	0
do_count	在看人数，如果是电视剧，默认值为0，如果是电影值为null	int	Y	Y	Y	0 / null
images	电影海报图，分别提供288px x 465px(大)，96px x 155px(中) 64px x 103px(小)尺寸	dict	Y	Y	Y	-
subtype	条目分类, movie或者tv	str	Y	Y	Y	movie
directors	导演，数据结构为影人的简化描述，见附录	array	Y	Y	Y	[]
casts	主演，最多可获得4个，数据结构为影人的简化描述，见附录	array	Y	Y	Y	[]
writers	编剧，数据结构为影人的简化描述，见附录	array	N	Y	Y	[]
website	官方网站	str	N	Y	Y	''
douban_site	豆瓣小站	str	Y	Y	Y	''
pubdates	如果条目类型是电影则为上映日期，如果是电视剧则为首Ï日期	array	N	Y	Y	[]
mainland_pubdate	大陆上映日期，如果条目类型是电影则为上映日期，如果是电视剧则为首播日期	str	N	Y	Y	''
pubdate	兼容性数据，未来会去掉，大陆上映日期，如果条目类型是电影则为上映日期，如果是电视剧则为首播日期	str	N	Y	Y	''
year	年代	str	Y	Y	Y	''
languages	语言	array	N	Y	Y	[]
durations	片长	array	N	Y	Y	[]
genres	影片类型，最多提供3个	array	Y	Y	Y	[]
countries	制片国家/地区	array	Y	Y	Y	[]
summary	简介	str	Y	Y	Y	''
comments_count	短评数量	int	Y	Y	Y	0
reviews_count	影评数量	int	Y	Y	Y	0
seasons_count	总季数(tv only)	int	Y	Y	Y	0 / null
current_season	当前季数(tv only)	int	Y	Y	Y	0 / null
episodes_count	当前季的集数(tv only)	int	Y	Y	Y	0 / null
schedule_url	影讯页URL(movie only)	str	Y	Y	Y	''
trailer_urls	预告片URL，对高级用户以上开放，最多开放4个地址	array	N	Y	Y	[]
clip_urls	片段URL，对高级用户以上开放，最多开放4个地址	array	N	Y	Y	[]
blooper_urls	花絮URL，对高级用户以上开放，最多开放4个地址	array	N	Y	Y	[]
photos	电影剧照，前10张，见附录	array	N	Y	Y	[]
popular_reviews	影评，前10条，影评结构，见附录	array	N	Y	Y	[]
 */
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
        @SerializedName("do_count") val doCount: Int,
        @SerializedName("share_url") val shareUrl: String,
        @SerializedName("seasons_count") val seasonsCount: Int,
        @SerializedName("schedule_url") val scheduleUrl: String,
        @SerializedName("episodes_count") val episodesCount: Int,
        @SerializedName("countries") val countries: List<String>,
        @SerializedName("genres") val genres: List<String>,
        @SerializedName("collect_count") val collectCount: Int,
        @SerializedName("casts") val casts: List<Cast>,
        @SerializedName("current_season") val currentSeason: Int,
        @SerializedName("original_title") val originalTitle: String,
        @SerializedName("summary") val summary: String,
        @SerializedName("subtype") val subtype: String,
        @SerializedName("directors") val directors: List<Director>,
        @SerializedName("comments_count") val commentsCount: Int,
        @SerializedName("ratings_count") val ratingsCount: Int,
        @SerializedName("aka") val aka: List<String>
)

data class DetailHead(val title: String,
                      val originalTitle: String,
                      val year: String,
                      val ratingCount:Int,
                      val rating: Rating) : MultiItemEntity {
    override fun getItemType(): Int {
        return MovieDetailAdapter.HEAD
    }
}

data class DetailSummary(val summary: String) : MultiItemEntity {
    override fun getItemType(): Int {
        return MovieDetailAdapter.SUMMARY
    }
}

data class DetailCasts(val directors: List<Director>,
                       val casts: List<Cast>) : MultiItemEntity {
    override fun getItemType(): Int {
        return MovieDetailAdapter.CASTS
    }
}