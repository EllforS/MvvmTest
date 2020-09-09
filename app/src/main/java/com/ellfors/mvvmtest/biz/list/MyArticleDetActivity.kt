package com.ellfors.mvvmtest.biz.list

import android.annotation.SuppressLint
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.databinding.ArticleDetBinding
import com.ellfors.mvvmtest.widget.CommonToolBar
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**
 * MyArticleDetActivity
 * 2020-06-05 17:18
 */
class MyArticleDetActivity : BaseActivity<ArticleDetBinding>(), CommonToolBar.ToolBarNormalListener {

    companion object {
        fun start(activity: AppCompatActivity, url: String?) {
            val intent = Intent(activity, MyArticleDetActivity::class.java)
            intent.putExtra("url", url)
            activity.startActivity(intent)
        }
    }

    override val getLayout: Int
        get() = R.layout.activity_article_det

    override fun initData() {
        val url = intent?.getStringExtra("url")

        setWebSetting()
        mBinding.webview.let {
            it.webChromeClient = webChromeClient
            it.webViewClient = webViewClient
        }

        if (!url.isNullOrEmpty())
            mBinding.url = url
    }

    override fun observerUI() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.webview.run {
            clearCache(true)
            stopLoading()
            destroy()
            null
        }
    }

    /**
     * 构建WebSetting
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebSetting() {
        mBinding.webview.settings.let {
            it.javaScriptEnabled = true //支持JS
            it.javaScriptCanOpenWindowsAutomatically =
                true //设置js可以直接打开窗口，如window.open()，默认为false
            it.defaultTextEncodingName = "utf-8" //设置默认编码
            it.loadsImagesAutomatically = true //支持自动加载图片
            it.useWideViewPort = true //设置此属性，可任意比例缩放。大视图模式
            it.loadWithOverviewMode = true //和setUseWideViewPort(true)一起解决网页自适应问题
            it.setAppCacheEnabled(false) //是否使用缓存
            it.domStorageEnabled = true //DOM Storage
            it.useWideViewPort = true
            it.loadWithOverviewMode = true
            it.cacheMode = WebSettings.LOAD_NO_CACHE //不使用缓存
            it.setSupportZoom(true) // 设置可以支持缩放
            it.builtInZoomControls = true // 设置出现缩放工具
            it.useWideViewPort = true //设置可在大视野范围内上下左右拖动，并且可以任意比例缩放
            it.loadWithOverviewMode = true //设置默认加载的可视范围是大视野范围
            it.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN //自适应屏幕
        }
    }

    /**
     * 构建webViewClient
     */
    var webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            view.visibility = View.VISIBLE
        }

        override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
            view.visibility = View.GONE
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    /**
     * 构建webChromeClient
     */
    var webChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            mBinding.progress.apply {
                progress = newProgress
                if (newProgress == 0)
                    visibility = View.VISIBLE
                if (newProgress == 100)
                    visibility = View.GONE
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //其中webView.canGoBack()在webView含有一个可后退的浏览记录时返回true
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mBinding.webview.canGoBack()) {
                mBinding.webview.goBack()
                return true
            } else {
                finish()
            }
        }
        return true
    }

    override fun onBackClick(view: View?) {
        onKeyDown(KeyEvent.KEYCODE_BACK, null)
    }

    override fun onPositiveClick(view: View?) {
    }
}