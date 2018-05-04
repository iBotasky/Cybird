package com.sirius.cybird.net.response

import com.google.gson.annotations.SerializedName

/**
 * Created By Botasky 2018/5/4
 */
data class Images(
        @SerializedName("small") val small: String, //https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp
        @SerializedName("large") val large: String, //https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp
        @SerializedName("medium") val medium: String //https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp
)