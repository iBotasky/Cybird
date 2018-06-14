package com.sirius.cybird.net.response

import com.google.gson.annotations.SerializedName


data class OneHomeData(
        @SerializedName("res") val res: Int,
        @SerializedName("data") val data: List<String>
)