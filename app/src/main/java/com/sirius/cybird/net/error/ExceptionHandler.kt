package com.sirius.cybird.net.error

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 *
 *Create by Botasky 2019/1/29
 */
object ExceptionHandler {
    //HTTP 错误
    private val HTTP_SERVER_ERROR = 505 //服务器发生错误
    private val HTTP_URL_NO_EXIST = 404 //请求地址不存在
    private val HTTP_REQUEST_DENY = 403 //请求被服务器拒绝
    private val HTTP_UNAUTHORIZED = 401 //未授权
    private val HTTP_REDIRECT = 307 //请求被重定向到其他页面

    fun handleExceptions(t: Throwable): String {
        return t.let {
            when (it) {
                is UnknownHostException -> "网络不可用"
                is SocketTimeoutException -> "请求网络超时"
                is HttpException -> convertStatusCode(it)
                is JsonParseException, is ParseException, is JSONException -> "数据解析错误"
                else -> "UNKNOWN EXCEPTION (NOT HTTP EXCEPTION)"
            }
        }
    }

    private fun convertStatusCode(httpException: HttpException): String {
        httpException.printStackTrace()
        return httpException.code().let {
            when (it) {
                HTTP_SERVER_ERROR -> "服务器发生错误"
                HTTP_URL_NO_EXIST -> "请求地址不存在"
                HTTP_REQUEST_DENY -> "请求被服务器拒绝"
                HTTP_UNAUTHORIZED -> "未授权"
                HTTP_REDIRECT -> "请求被重定向到其他页面"
                else -> "UNKNOWN HTTP EXCEPTION"
            }
        }

    }

}