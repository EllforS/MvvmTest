package com.ellfors.mvvmtest.utils

import android.text.TextUtils
import android.util.Log
import com.ellfors.mvvmtest.BuildConfig
import com.orhanobut.logger.Logger

/**
 * HttpLogUtil
 * 2020-07-10 15:51
 */
object HttpLogUtil {

    /**
     * 不打印的Tag
     */
    private val TYEPS = arrayOf(
        "--> END GET",
        "<-- END HTTP",
        "Access-Control-Allow-Origin:",
        "Access-Control-Expose-Headers:",
        "Connection:",
        "Content-Length:",
        "Content-Type:",
        "Cache-Control:",
        "Cache-Control:",
        "Date:",
        "EagleId:",
        "Expires:",
        "Timing-Allow-Origin:",
        "Transfer-Encoding:",
        "Server:",
        "Set-Cookie:",
        "Vary:",
        "Via:",
        "X-Cache:",
        "X-Swift-CacheTime:",
        "X-Swift-SaveTime:"
    )

    fun log(tag: String?, message: String) {
        //过滤掉无用信息
        if (!filterType(message))
            return
        if (message.substring(0, 1) == "{")
            Logger.json(message)
        else
            Log.i(tag, message)
    }

    /**
     * 过滤类型
     */
    private fun filterType(message: String): Boolean {
        if (TextUtils.isEmpty(message))
            return false
        for (str in TYEPS) {
            if (message.startsWith(str))
                return false
        }
        return true
    }
}