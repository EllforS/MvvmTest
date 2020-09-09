package com.ellfors.mvvmtest.bindadp

import androidx.databinding.BindingAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

/**
 * requireAll = false
 * 以指定不是每个属性都必须分配一个绑定表达式
 * 就是不用写全也没关系
 */
@BindingAdapter(value = ["onRefresh", "onLoadMore"], requireAll = false)
fun SmartRefreshLayout.onRefreshLoadMore(onRefreshListener: OnRefreshListener?, onLoadMoreListener: OnLoadMoreListener?) {
    setOnRefreshListener(onRefreshListener)
    setOnLoadMoreListener(onLoadMoreListener)
}

@BindingAdapter(value = ["refreshing", "moreLoading", "hasMore"])
fun SmartRefreshLayout.bindSmartRefreshLayout(refreshing: Boolean, moreLoading: Boolean, hasMore: Boolean) {
    if (!refreshing)
        finishRefresh()
    if (!moreLoading)
        finishLoadMore()
    setEnableLoadMore(hasMore)
}