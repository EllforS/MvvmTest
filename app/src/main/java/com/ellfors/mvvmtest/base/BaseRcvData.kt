package com.ellfors.mvvmtest.base

/**
 * BaseRcvData
 * 2020-06-04 11:49
 */
class BaseRcvData {
    var data: Any? = null
    var type: Int = 0

    constructor()

    constructor(data: Any?, type: Int) {
        this.data = data
        this.type = type
    }
}