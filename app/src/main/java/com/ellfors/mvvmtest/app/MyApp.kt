package com.ellfors.mvvmtest.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.ellfors.mvvmtest.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.smtt.sdk.QbSdk

/**
 * MyApp
 * 2020-05-19 11:04
 */
class MyApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initX5WebView()
        initRefreshLayout()
    }

    private fun initX5WebView() {
        QbSdk.initX5Environment(applicationContext, null)
    }

    /**
     * 初始化刷新加载控件
     */
    private fun initRefreshLayout() {
        //设置刷新文字
        ClassicsHeader.REFRESH_HEADER_PULLING = "下拉刷新"
        ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在刷新"
        ClassicsHeader.REFRESH_HEADER_LOADING = "正在刷新"
        ClassicsHeader.REFRESH_HEADER_RELEASE = "放开刷新"
        ClassicsHeader.REFRESH_HEADER_FINISH = "刷新完成"
        ClassicsHeader.REFRESH_HEADER_FAILED = "刷新失败"
        //设置加载文字
        ClassicsFooter.REFRESH_FOOTER_PULLING = "上拉加载"
        ClassicsFooter.REFRESH_FOOTER_RELEASE = "松开加载"
        ClassicsFooter.REFRESH_FOOTER_LOADING = "正在加载"
        ClassicsFooter.REFRESH_FOOTER_REFRESHING = "正在加载"
        ClassicsFooter.REFRESH_FOOTER_FINISH = "加载完成"
        ClassicsFooter.REFRESH_FOOTER_FAILED = "加载失败"
        ClassicsFooter.REFRESH_FOOTER_NOTHING = "没有更多数据了~"
        //全局属性设置
        SmartRefreshLayout.setDefaultRefreshInitializer { _, layout -> //是否在刷新/加载的时候禁止列表的操作
            layout.setDisableContentWhenRefresh(true)
            layout.setDisableContentWhenLoading(true)
            //Header、Footer高度
            layout.setHeaderHeight(70F)
            layout.setFooterHeight(70F)
            //数据不满一页时取消上拉加载功能
            layout.setEnableLoadMoreWhenContentNotFull(false)
            //禁止自动上拉加载（需手动）
            layout.setEnableAutoLoadMore(true)
        }
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, _ ->
            ClassicsHeader(this)
                .setEnableLastTime(false)//是否显示刷新时间
                .setTextSizeTitle(12F)//文字大小
                .setDrawableMarginRight(7F)//文字与图片间距
                .setFinishDuration(0)//显示刷新完成的持续时间
                .setAccentColor(resources.getColor(R.color.color_333333))//文字颜色
                .setArrowDrawable(resources.getDrawable(R.drawable.refresh_pull_icon))//箭头图片
                .setProgressDrawable(resources.getDrawable(R.drawable.refresh_loading_icon))//进度图片
        }
        //设置全局Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, _ ->
            ClassicsFooter(this)
                .setTextSizeTitle(12F) //文字大小
                .setDrawableMarginRight(7F) //文字与图片间距
                .setFinishDuration(0) //显示刷新完成的持续时间
                .setAccentColor(resources.getColor(R.color.color_333333)) //文字颜色
                .setArrowDrawable(resources.getDrawable(R.drawable.refresh_pull_icon)) //箭头图片
                .setProgressDrawable(resources.getDrawable(R.drawable.refresh_loading_icon)) //进度图片
        }
    }

}