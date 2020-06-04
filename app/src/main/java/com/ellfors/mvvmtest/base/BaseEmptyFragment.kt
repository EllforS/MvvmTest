package com.ellfors.mvvmtest.base

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ellfors.mvvmtest.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * BaseEmptyFragment
 * 2020-05-25 18:25
 */
abstract class BaseEmptyFragment : Fragment() {

    var view_empty: View? = null
    var view_net_error: View? = null

    private var isShowingReplaceView = false
    private var targetView: View? = null
    private var viewParent: ViewGroup? = null
    private var viewIndex = -1

    /**
     * 显示无数据页
     */
    open fun showEmptyError(tips: String?, drawableRes: Int) {
        if (isShowingReplaceView || targetView == null || viewIndex == -1) return
        if (view_empty == null) {
            view_empty = LayoutInflater.from(activity).inflate(R.layout.layout_default_empty, null)
            view_empty!!.setOnClickListener(null)
            val layoutParams = targetView!!.layoutParams
            view_empty!!.layoutParams = layoutParams
        }
        val tvTips = view_empty!!.findViewById<View>(R.id.tv_tips) as TextView
        val ivImg = view_empty!!.findViewById<View>(R.id.iv_img) as ImageView
        if (!TextUtils.isEmpty(tips)) tvTips.text = tips
        if (drawableRes != 0) ivImg.setImageResource(drawableRes)
        if (viewParent is SmartRefreshLayout) {
            (viewParent as SmartRefreshLayout).setRefreshContent(view_empty!!)
        } else {
            viewParent!!.removeViewAt(viewIndex)
            viewParent!!.addView(view_empty, viewIndex)
        }
        isShowingReplaceView = true
    }

    /**
     * 显示无数据页
     */
    open fun showEmptyError() {
        this.showEmptyError("", 0)
    }

    /**
     * 显示无网络页
     */
    open fun showNetError() {
        if (isShowingReplaceView || targetView == null || viewIndex == -1) return
        if (view_net_error == null) {
            view_net_error = LayoutInflater.from(activity).inflate(R.layout.layout_net_error, null)
            view_net_error!!.setOnClickListener(null)
            val layoutParams = targetView!!.layoutParams
            view_net_error!!.layoutParams = layoutParams
        }
        if (viewParent is SmartRefreshLayout) {
            (viewParent as SmartRefreshLayout).setRefreshContent(view_net_error!!)
        } else {
            viewParent!!.removeViewAt(viewIndex)
            viewParent!!.addView(view_net_error, viewIndex)
        }
        isShowingReplaceView = true
    }

    /**
     * 显示内容页
     */
    open fun showContent() {
        if (targetView != null && isShowingReplaceView) {
            if (viewParent is SmartRefreshLayout) {
                (viewParent as SmartRefreshLayout).setRefreshContent(targetView!!)
            } else {
                viewParent!!.removeViewAt(viewIndex)
                viewParent!!.addView(targetView, viewIndex)
            }
            isShowingReplaceView = false
        }
    }

    /**
     * 重新请求数据
     */
    open fun resetData() {}

    private fun getTargetViewIndex(): Int {
        if (targetView == null) return -1
        val viewGroup = targetView!!.parent as ViewGroup
        val childCount = viewGroup.childCount
        for (i in 0 until childCount) {
            if (viewGroup.getChildAt(i) === targetView) return i
        }
        return -1
    }

    /**
     * 设置需要替换的View
     */
    fun setTargetView(view: View?) {
        targetView = view
        viewIndex = getTargetViewIndex()
        viewParent = targetView!!.parent as ViewGroup
    }
}