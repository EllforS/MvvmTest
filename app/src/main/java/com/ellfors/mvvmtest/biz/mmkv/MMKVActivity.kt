package com.ellfors.mvvmtest.biz.mmkv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.databinding.ActivityMmkvBinding
import com.ellfors.mvvmtest.vm.VMInjectUtil
import com.ellfors.mvvmtest.vm.viewmodel.MMKVVM

/**
 * MMKVActivity
 * 2020-11-30 10:32
 */
class MMKVActivity : BaseActivity<ActivityMmkvBinding>() {

    companion object {
        fun start(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, MMKVActivity::class.java))
        }
    }

    val mViewModel: MMKVVM by lazy {
        VMInjectUtil.injectMMKVVM(this)
    }

    override val getLayout: Int
        get() = R.layout.activity_mmkv

    override fun initData() {
        mBinding.vm = mViewModel
    }

    override fun observerUI() {

    }
}