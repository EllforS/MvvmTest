package com.ellfors.mvvmtest.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.app.MyApp

/**
 * TsUtil
 * 2020-09-08 11:39
 */
object TsUtil {

    private var mToast: Toast? = null
    private var mToastY = 0 // toast默认显示高度

    fun showToast(text: String?) {
        showToast(text, 0, 0)
    }

    fun cancelToast() {
        mToast?.cancel()
    }

    @SuppressLint("ShowToast")
    fun showToast(text: String?, iconResId: Int, duration: Int) {
        if (text.isNullOrEmpty())
            return
        var tv: TextView
        if (mToast == null) {
            mToast = Toast.makeText(MyApp.context, text, duration)
            val inflate =
                MyApp.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            tv = inflate.inflate(R.layout.dialog_toast, null) as TextView
            mToast!!.view = tv
            mToastY = mToast!!.yOffset
        }
        tv = mToast!!.view as TextView
        tv.text = text
        var icon: Drawable? = null
        if (iconResId > 0) {
            try {
                icon = MyApp.context.resources.getDrawable(iconResId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            mToast?.setGravity(Gravity.CENTER, 0, 0)
        } else {
            mToast?.setGravity(Gravity.BOTTOM, 0, mToastY)
        }
        tv.setCompoundDrawablesWithIntrinsicBounds(null, icon, null, null)
        mToast?.show()
    }
}