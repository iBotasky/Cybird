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








