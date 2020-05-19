package com.ellfors.mvvmtest.biz

import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.biz.img.MyImageActivity
import com.ellfors.mvvmtest.biz.list.MyArticleActivity
import com.ellfors.mvvmtest.biz.time.TimeDownActivity
import com.ellfors.mvvmtest.databinding.MainBinding

/**
 * MainActivity
 * 2020-05-19 11:05
 */
class MainActivity : BaseActivity<MainBinding>() {

    override val getLayout: Int
        get() = R.layout.activity_main

    override fun initData() {
        mBinding.activity = this
    }

    fun toImg() {
        MyImageActivity.start(this)
    }

    fun toList() {
        MyArticleActivity.start(this)
    }

    fun toTime() {
        TimeDownActivity.start(this)
    }

    override fun observerUI() {

    }

}