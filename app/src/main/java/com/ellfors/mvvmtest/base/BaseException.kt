package com.ellfors.mvvmtest.base

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

    constructor(message: String?, errorCode: Int) : super(message) {
        this.errorCode = errorCode
        url = ""
        data = null
    }

    constructor(message: String?, errorCode: Int, url: String?) : super(message) {
        this.errorCode = errorCode
        this.url = url
        data = null
    }

    constructor(t: Exception?) : super(t?.message) {
        this.errorCode = -100
        this.url = ""
        this.data = null
    }

    constructor(t: Throwable?) : super(t?.message) {
        this.errorCode = -100
        this.url = ""
        this.data = null
    }

}