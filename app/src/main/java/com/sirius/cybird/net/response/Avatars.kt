package com.sirius.cybird.net.response

import com.google.gson.annotations.SerializedName

/**
 * Created By Botasky 2018/5/4
 */
data class Avatars(
        @SerializedName("small") val small: String, //https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp
        @SerializedName("large") val large: String, //https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp
        @SerializedName("medium") val medium: String //https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp
)