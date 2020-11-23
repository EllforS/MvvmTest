package com.ellfors.mvvmtest.biz.img

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.databinding.ImageBinding
import com.ellfors.mvvmtest.vm.VMInjectUtil
import com.ellfors.mvvmtest.vm.viewmodel.ImageVM

/**
 * ImageActivity
 * 2020-05-19 11:38
 */
class MyImageActivity : BaseActivity<ImageBinding>() {

    private val mViewModel: ImageVM by lazy {
        VMInjectUtil.injectImageVM(this)
    }

    companion object {

        fun start(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, MyImageActivity::class.java))
        }
    }

    override val getLayout: Int
        get() = R.layout.activity_img

    override fun initData() {
        mBinding.vm = mViewModel
        observerUI()
        mViewModel.refreshImage()
    }

    override fun observerUI() {
        mViewModel.run {
            girl.observe(this@MyImageActivity, Observer {
                Log.d("AAA", "图片地址：$it")
            })
            mException.observe(this@MyImageActivity, Observer {

            })
        }
    }
}