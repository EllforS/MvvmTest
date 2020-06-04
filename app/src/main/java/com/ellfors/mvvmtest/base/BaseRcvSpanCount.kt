package com.ellfors.mvvmtest.base

/**
 * BaseRecyclerTypeSpanCount
 * 2020-06-04 12:12
 */
class BaseRcvSpanCount {

    var type = 0        //类型
    var spanCount = 0   //权重

    constructor(type: Int, spanCount: Int) {
        this.type = type
        this.spanCount = spanCount
    }

}