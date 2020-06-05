package com.ellfors.mvvmtest.bindadp

import androidx.databinding.BindingAdapter
import com.tencent.smtt.sdk.WebView

/**
 * WebViewBindingAdapter
 * 2020-06-05 17:48
 */
@BindingAdapter("loadUrl")
fun WebView.loadUrl(url: String?) {
    loadUrl(url ?: "")
}