package com.sirius.cybird.net.response

import com.google.gson.annotations.SerializedName

/**
 * Created By Botasky 2018/5/4
 */
data class Rating(
        @SerializedName("max") val max: Int, //10
        @SerializedName("average") val average: Double, //9.6
        @SerializedName("stars") val stars: String, //50
        @SerializedName("min") val min: Int //0
)