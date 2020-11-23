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

    companion object {
        fun <T> build(datas: List<T>?, type: Int): List<BaseRcvData> {
            val newDatas = arrayListOf<BaseRcvData>()
            if (datas.isNullOrEmpty())
                return newDatas
            for (i in datas) {
                newDatas.add(BaseRcvData(i, type))
            }
            return newDatas
        }
    }
}