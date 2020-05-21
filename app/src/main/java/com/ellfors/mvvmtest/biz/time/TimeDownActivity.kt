package com.ellfors.mvvmtest.biz.time

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.databinding.TimeDownBinding
import com.ellfors.mvvmtest.vm.InjectUtils
import com.ellfors.mvvmtest.vm.viewmodel.TimeDownVM

/**
 * TimeDownActivity
 * 2020-05-19 16:54
 */
class TimeDownActivity : BaseActivity<TimeDownBinding>() {

    val mViewModel: TimeDownVM by lazy {
        InjectUtils.injectTimeDownVM(this)
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, TimeDownActivity::class.java))
        }
    }

    override val getLayout: Int
        get() = R.layout.activity_time_down

    override fun initData() {
        mBinding.viewModel = mViewModel
    }

    override fun observerUI() {

    }
}