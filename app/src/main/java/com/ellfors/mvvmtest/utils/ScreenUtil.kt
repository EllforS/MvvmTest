package com.ellfors.mvvmtest.utils

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import androidx.annotation.NonNull

/**
 * 适配工具类
 * 2020-05-25 18:15
 */
object ScreenUtil {
    private var sNonCompatDensity = 0f
    private var sNonScaledDensity = 0f

    fun setCustomDensity(@NonNull activity: Activity, application: Application) {
        val appDisplayMetrics = application.resources.displayMetrics
        if (sNonCompatDensity == 0f) {
            sNonCompatDensity = appDisplayMetrics.density
            sNonScaledDensity = appDisplayMetrics.scaledDensity
            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(newConfig: Configuration) {
                    if (newConfig.fontScale > 0) {
                        sNonScaledDensity =
                            application.resources.displayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {

                }
            })
        }
        val targetDensity = appDisplayMetrics.widthPixels.toFloat() / 360
        val targetScaleDensity =
            targetDensity * (sNonScaledDensity / sNonCompatDensity)
        val targetDpi = (160 * targetDensity).toInt()
        appDisplayMetrics.density = targetDensity
        appDisplayMetrics.densityDpi = targetDpi
        appDisplayMetrics.scaledDensity = targetScaleDensity
        val activityDisplayMetrics = activity.resources.displayMetrics
        activityDisplayMetrics.density = targetDensity
        activityDisplayMetrics.densityDpi = targetDpi
        activityDisplayMetrics.scaledDensity = targetScaleDensity
    }
}