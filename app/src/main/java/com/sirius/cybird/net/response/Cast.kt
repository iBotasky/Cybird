package com.sirius.cybird.net.response

import com.google.gson.annotations.SerializedName

/**
 * Created By Botasky 2018/5/4
 */
data class Cast(
        @SerializedName("alt") val alt: String, //https://movie.douban.com/celebrity/1054521/
        @SerializedName("avatars") val avatars: Avatars,
        @SerializedName("name") val name: String, //蒂姆·罗宾斯
        @SerializedName("id") val id: String //1054521
)