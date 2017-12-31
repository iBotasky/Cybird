package com.sirius.cybird.net.response
import com.google.gson.annotations.SerializedName


/**
 *
 *Create By Botasky 31/12/2017
 **/
data class ZhiHuData(
		@SerializedName("date") val date: String, //20171231
		@SerializedName("stories") val stories: List<Story>,
		@SerializedName("top_stories") val topStories: List<TopStory>
)

data class Story(
		@SerializedName("images") val images: List<String>,
		@SerializedName("type") val type: Int, //0
		@SerializedName("id") val id: Int, //9662519
		@SerializedName("ga_prefix") val gaPrefix: String, //123110
		@SerializedName("title") val title: String //看见好朋友和别人走得近了，心里会有点不爽，对吧？
)

data class TopStory(
		@SerializedName("image") val image: String, //https://pic4.zhimg.com/v2-fdeb7b0db21cb9a8c1f6bc370ed93f47.jpg
		@SerializedName("type") val type: Int, //0
		@SerializedName("id") val id: Int, //9663397
		@SerializedName("ga_prefix") val gaPrefix: String, //123107
		@SerializedName("title") val title: String //孩子撞见父母「啪啪啪」怎么办？
)