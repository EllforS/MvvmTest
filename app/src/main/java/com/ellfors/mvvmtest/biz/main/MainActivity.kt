package com.ellfors.mvvmtest.biz.main

import androidx.recyclerview.widget.LinearLayoutManager
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.databinding.MainBinding

/**
 * MainActivity
 * 2020-05-19 11:05
 */
class MainActivity : BaseActivity<MainBinding>() {

    override val getLayout: Int
        get() = R.layout.activity_main

    override fun initData() {
        mBinding.rcvMain.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(this@MainActivity)
        }
    }

    override fun observerUI() {

    }

}