package com.sirius.cybird.utils

import android.annotation.SuppressLint
import android.app.AppOpsManager
import android.content.Context

/**
 *判断是否拥有通知权限
 * 通知权限跳转申请：https://stackoverflow.com/questions/32366649/any-way-to-link-to-the-android-notification-settings-for-my-app
 *Create by Botasky 2018/8/9
 */
class NotificationUtils {
    private val CHECK_OP_NO_THROW = "checkOpNoThrow"
    private val OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION"

    @SuppressLint("NewApi")
    fun isNotificationEnabled(context: Context): Boolean {

        val mAppOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager

        val appInfo = context.applicationInfo
        val pkg = context.applicationContext.packageName
        val uid = appInfo.uid
        var appOpsClass: Class<*>? = null

        /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager::class.java.name)

            val checkOpNoThrowMethod = appOpsClass!!.getMethod(CHECK_OP_NO_THROW,
                    Integer.TYPE, Integer.TYPE, String::class.java)

            val opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION)
            val value = opPostNotificationValue.get(Int::class.java) as Int

            return checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) as Int == AppOpsManager.MODE_ALLOWED
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }
}