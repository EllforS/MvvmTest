package com.ellfors.mvvmtest.base

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ellfors.mvvmtest.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * EmptyViewHelper
 * 2020-11-23 09:33
 */
class EmptyViewHelper(val mContext: Context) {

    var viewEmpty: View? = null
    var viewNetError: View? = null

    private var isShowingReplaceView = false
    private var targetView: View? = null
    private var viewParent: ViewGroup? = null
    private var viewIndex = -1

    /**
     * 显示无数据页
     */
    fun showEmptyError(tips: String?, drawableRes: Int) {
        if (isShowingReplaceView || targetView == null || viewIndex == -1) return
        if (viewEmpty == null) {
            viewEmpty = LayoutInflater.from(mContext).inflate(R.layout.layout_default_empty, null)
            viewEmpty?.setOnClickListener(null)
            val layoutParams = targetView?.layoutParams
            viewEmpty?.layoutParams = layoutParams
        }
        val tvTips = viewEmpty?.findViewById<View>(R.id.tv_tips) as TextView
        val ivImg = viewEmpty?.findViewById<View>(R.id.iv_img) as ImageView
        if (!TextUtils.isEmpty(tips)) tvTips.text = tips
        if (drawableRes != 0) ivImg.setImageResource(drawableRes)
        if (viewParent is SmartRefreshLayout) {
            (viewParent as SmartRefreshLayout).setRefreshContent(viewEmpty!!)
        } else {
            viewParent?.removeViewAt(viewIndex)
            checkChildHasParent(viewEmpty!!)
            viewParent?.addView(viewEmpty, viewIndex)
        }
        isShowingReplaceView = true
    }

    /**
     * 显示无网络页
     */
    fun showNetError() {
        if (isShowingReplaceView || targetView == null || viewIndex == -1) return
        if (viewNetError == null) {
            viewNetError = LayoutInflater.from(mContext).inflate(R.layout.layout_net_error, null)
            viewNetError?.setOnClickListener(null)
            val layoutParams = targetView?.layoutParams
            viewNetError?.layoutParams = layoutParams
        }
        if (viewParent is SmartRefreshLayout) {
            (viewParent as SmartRefreshLayout).setRefreshContent(viewNetError!!)
        } else {
            viewParent?.removeViewAt(viewIndex)
            checkChildHasParent(viewNetError!!)
            viewParent?.addView(viewNetError, viewIndex)
        }
        isShowingReplaceView = true
    }

    /**
     * 显示内容页
     */
    fun showContent() {
        if (targetView != null && isShowingReplaceView) {
            if (viewParent is SmartRefreshLayout) {
                (viewParent as SmartRefreshLayout).setRefreshContent(targetView!!)
            } else {
                viewParent?.removeViewAt(viewIndex)
                checkChildHasParent(targetView!!)
                viewParent?.addView(targetView, viewIndex)
            }
            isShowingReplaceView = false
        }
    }

    /**
     * 设置需要替换的View
     */
    fun setTargetView(view: View?) {
        targetView = view
        viewIndex = getTargetViewIndex()
        viewParent = targetView?.parent as ViewGroup
    }

    private fun getTargetViewIndex(): Int {
        if (targetView == null) return -1
        val viewGroup = targetView?.parent as ViewGroup
        val childCount = viewGroup.childCount
        for (i in 0 until childCount) {
            if (viewGroup.getChildAt(i) === targetView) return i
        }
        return -1
    }

    private fun checkChildHasParent(view: View) {
        if (view.parent != null && view.parent is ViewGroup)
            (view.parent as ViewGroup).removeView(view)
    }
}