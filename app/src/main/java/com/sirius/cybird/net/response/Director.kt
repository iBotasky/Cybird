package com.sirius.cybird.net.response

import com.google.gson.annotations.SerializedName

/**
 * Created By Botasky 2018/5/4
 */
data class Director(
        @SerializedName("alt") val alt: String, //https://movie.douban.com/celebrity/1047973/
        @SerializedName("avatars") val avatars: Avatars,
        @SerializedName("name") val name: String, //弗兰克·德拉邦特
        @SerializedName("id") val id: String //1047973
)