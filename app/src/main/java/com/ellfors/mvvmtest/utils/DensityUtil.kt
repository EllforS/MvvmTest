package com.ellfors.mvvmtest.utils

import android.util.TypedValue
import com.ellfors.mvvmtest.app.MyApp

/**
 * DensityUtil
 * 2020-05-19 11:52
 */
object DensityUtil {

    /**
     * dp转px
     */
    fun dp2px(dpVal: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpVal, MyApp.context.resources.displayMetrics
        ).toInt()
    }

    /**
     * sp转px
     */
    fun sp2px(spVal: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spVal, MyApp.context.resources.displayMetrics
        ).toInt()
    }

    /**
     * px转dp
     */
    fun px2dp(pxVal: Float): Float {
        val scale = MyApp.context.resources.displayMetrics.density
        return pxVal / scale
    }

    /**
     * px转sp
     */
    fun px2sp(pxVal: Float): Float {
        return pxVal / MyApp.context.resources.displayMetrics.scaledDensity
    }

}