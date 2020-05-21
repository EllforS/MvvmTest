package com.ellfors.mvvmtest.bindadp

import androidx.databinding.BindingAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

/**
 * SmartRefreshLayoutBindingAdapter
 * 2020-05-19 17:58
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