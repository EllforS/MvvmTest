package com.ellfors.mvvmtest.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.ellfors.mvvmtest.utils.ScreenUtil

/**
 * BaseActivity
 * 2020-04-15 12:11
 */
abstract class BaseActivity<T : ViewDataBinding> : BaseEmptyActivity() {

    lateinit var mBinding: T
    private var mEditTextTouchOutSide = false

    override fun onCreate(savedInstanceState: Bundle?) {
        ScreenUtil.setCustomDensity(this, application)
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayout)
        mBinding.lifecycleOwner = this  //妈的，不加这句无法刷新UI

        initData()
        observerUI()
    }

    abstract val getLayout: Int

    abstract fun initData()

    abstract fun observerUI()

    /**
     * 设置EditText点击外部是否收起软键盘
     */
    open fun setEditTextTouchOutSide(touchOutSide: Boolean) {
        this.mEditTextTouchOutSide = touchOutSide
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            //调用方法判断是否需要隐藏键盘
            if (mEditTextTouchOutSide)
                hideKeyboard(ev, view, this)
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 点击EditText外部隐藏软键盘的方法
     */
    private fun hideKeyboard(event: MotionEvent, view: View?, activity: Activity) {
        try {
            if (view is EditText) {
                val location = intArrayOf(0, 0)
                view.getLocationInWindow(location)
                val left = location[0]
                val top = location[1]
                val right = left + view.getWidth()
                val bootom = top + view.getHeight()
                // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
                if (event.rawX < left || event.rawX > right || event.y < top || event.rawY > bootom) {
                    // 隐藏键盘
                    val token = view.getWindowToken()
                    val inputMethodManager =
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}