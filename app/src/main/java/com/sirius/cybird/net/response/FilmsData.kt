package com.sirius.cybird.net.response
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.google.gson.annotations.SerializedName
import com.sirius.cybird.module.RvItemType


/**
 * Description：
 * Created by Botasky on 2017/12/29.
 */

data class FilmsData(
		@SerializedName("count") val count: Int, //3
		@SerializedName("mStart") val start: Int, //0
		@SerializedName("total") val total: Int, //250
		@SerializedName("subjects") val films: List<Film>,
		@SerializedName("title") val title: String //豆瓣电影Top250
)

data class Film(
		@SerializedName("rating") val rating: Rating,
		@SerializedName("genres") val genres: List<String>,
		@SerializedName("title") val title: String, //肖申克的救赎
		@SerializedName("casts") val casts: List<Cast>,
		@SerializedName("collect_count") val collectCount: Int, //1181011
		@SerializedName("original_title") val originalTitle: String, //The Shawshank Redemption
		@SerializedName("subtype") val subtype: String, //movie
		@SerializedName("directors") val directors: List<Director>,
		@SerializedName("year") val year: String, //1994
		@SerializedName("images") val images: Images,
		@SerializedName("alt") val alt: String, //https://movie.douban.com/subject/1292052/
		@SerializedName("id") val id: String //1292052
):MultiItemEntity{
	override fun getItemType(): Int {
		return RvItemType.MOVIE_HOT
	}

}

data class Images(
		@SerializedName("small") val small: String, //https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp
		@SerializedName("large") val large: String, //https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp
		@SerializedName("medium") val medium: String //https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp
)

data class Rating(
		@SerializedName("max") val max: Int, //10
		@SerializedName("average") val average: Double, //9.6
		@SerializedName("stars") val stars: String, //50
		@SerializedName("min") val min: Int //0
)

data class Cast(
		@SerializedName("alt") val alt: String, //https://movie.douban.com/celebrity/1054521/
		@SerializedName("avatars") val avatars: Avatars,
		@SerializedName("name") val name: String, //蒂姆·罗宾斯
		@SerializedName("id") val id: String //1054521
)

data class Avatars(
		@SerializedName("small") val small: String, //https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp
		@SerializedName("large") val large: String, //https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp
		@SerializedName("medium") val medium: String //https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp
)

data class Director(
		@SerializedName("alt") val alt: String, //https://movie.douban.com/celebrity/1047973/
		@SerializedName("avatars") val avatars: Avatars,
		@SerializedName("name") val name: String, //弗兰克·德拉邦特
		@SerializedName("id") val id: String //1047973
)
