package com.ellfors.mvvmtest.base

import com.ellfors.mvvmtest.utils.StringUtil
import java.io.IOException

/**
 * BaseException
 * 2020-05-19 16:19
 */
class BaseException : IOException {
    var url: String? = ""
    var errorCode: Int = 100
    var data: Any? = null

    constructor() : super()

    constructor(message: String?, errorCode: Int) : super(if (StringUtil.isContainChinese(message)) message else "") {
        this.errorCode = errorCode
        url = ""
        data = null
    }

    constructor(message: String?, errorCode: Int, url: String?) : super(if (StringUtil.isContainChinese(message)) message else "") {
        this.errorCode = errorCode
        this.url = url
        data = null
    }
}