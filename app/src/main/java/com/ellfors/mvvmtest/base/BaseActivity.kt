package com.ellfors.mvvmtest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

/**
 * BaseActivity
 * 2020-04-15 12:11
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayout)
        mBinding.lifecycleOwner = this  //妈的，不加这句无法刷新UI

        initData()
        observerUI()
    }

    abstract val getLayout: Int

    abstract fun initData()

    abstract fun observerUI()

}