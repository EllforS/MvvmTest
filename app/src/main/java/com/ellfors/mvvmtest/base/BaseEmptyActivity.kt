package com.ellfors.mvvmtest.base

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * BaseEmptyActivity
 * 2020-05-25 18:22
 */
abstract class BaseEmptyActivity : AppCompatActivity() {

    private val mEmptyViewHelper by lazy { EmptyViewHelper(this) }

    /**
     * 显示无数据页
     */
    fun showEmptyError(tips: String?, drawableRes: Int) {
        mEmptyViewHelper.showEmptyError(tips, drawableRes)
    }

    /**
     * 显示无数据页
     */
    fun showEmptyError() {
        mEmptyViewHelper.showEmptyError("", 0)
    }

    /**
     * 显示无网络页
     */
    fun showNetError() {
        mEmptyViewHelper.showNetError()
    }

    /**
     * 显示内容页
     */
    fun showContent() {
        mEmptyViewHelper.showContent()
    }

    /**
     * 设置需要替换的View
     */
    fun setTargetView(view: View?) {
        mEmptyViewHelper.setTargetView(view)
    }
}