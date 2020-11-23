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

    private val mEmptyViewHelper by lazy {
        activity?.let {
            EmptyViewHelper(it)
        }
    }

    /**
     * 显示无数据页
     */
    open fun showEmptyError(tips: String?, drawableRes: Int) {
        mEmptyViewHelper?.showEmptyError(tips, drawableRes)
    }

    /**
     * 显示无数据页
     */
    open fun showEmptyError() {
        mEmptyViewHelper?.showEmptyError("", 0)
    }

    /**
     * 显示无网络页
     */
    open fun showNetError() {
        mEmptyViewHelper?.showNetError()
    }

    /**
     * 显示内容页
     */
    open fun showContent() {
        mEmptyViewHelper?.showContent()
    }

    /**
     * 设置需要替换的View
     */
    fun setTargetView(view: View?) {
        mEmptyViewHelper?.setTargetView(view)
    }
}