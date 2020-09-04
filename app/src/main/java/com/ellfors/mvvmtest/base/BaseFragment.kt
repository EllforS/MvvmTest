package com.ellfors.mvvmtest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * BaseFragment
 * 2020-04-15 12:11
 */
abstract class BaseFragment<T : ViewDataBinding> : BaseEmptyFragment() {

    lateinit var mBinding: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayout, container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        observerUI()
    }

    abstract val getLayout: Int

    abstract fun initData()

    abstract fun observerUI()
}