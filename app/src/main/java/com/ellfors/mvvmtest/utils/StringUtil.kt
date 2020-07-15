package com.ellfors.mvvmtest.utils

import java.util.regex.Pattern

/**
 * StringUtil
 * 2020-07-15 17:59
 */
object StringUtil {
    /**
     * 判断字符串中是否包含中文
     * 不能校验是否为中文标点符号
     *
     * @param str 待校验字符串
     * @return 是否为中文
     */
    fun isContainChinese(str: String?): Boolean {
        if (str.isNullOrEmpty())
            return false
        val p = Pattern.compile("[\u4e00-\u9fa5]")
        val m = p.matcher(str)
        return m.find()
    }
}