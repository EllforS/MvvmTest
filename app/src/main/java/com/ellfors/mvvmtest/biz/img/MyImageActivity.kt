package com.ellfors.mvvmtest.biz.img

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.bean.Girl
import com.ellfors.mvvmtest.bindadp.loadImg
import com.ellfors.mvvmtest.databinding.ImageBinding
import com.ellfors.mvvmtest.vm.InjectUtils
import com.ellfors.mvvmtest.vm.viewmodel.ImageVM
import com.ellfors.mvvmtest.widget.CommonToolBar

/**
 * ImageActivity
 * 2020-05-19 11:38
 */
class MyImageActivity : BaseActivity<ImageBinding>(), CommonToolBar.CommonTopCallBack {

    private val mViewModel: ImageVM by lazy {
        InjectUtils.injectImageVM(this)
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

    override fun onBackClick(view: View?) {
        finish()
    }

    override fun observerUI() {
        mViewModel.let { vm ->
            vm.girl.observe(this, Observer {
                Log.d("AAA", "图片地址：$it")
            })
            vm.mException.observe(this, Observer {

            })
        }
    }
}