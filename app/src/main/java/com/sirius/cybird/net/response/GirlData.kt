package com.sirius.cybird.net.response

/**
 * Description：
 * Created by Botasky on 2017/12/29.
 */
data class GirlResopnse(val error: Boolean,
                        val results: List<ResultsBean>)

data class ResultsBean(val _id: String,
                       val createdAt: String,
                       val desc: String,
                       val publishedAt: String,
                       val source: String,
                       val type: String,
                       val url: String,
                       val used: Boolean,
                       val who: String)