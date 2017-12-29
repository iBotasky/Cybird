package com.sirius.cybird.data

/**
 * Description：
 * Created by Botasky on 2017/12/29.
 */
object SearchResultModel {
    data class Result(val query: Query)
    data class Query(val searchInfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}