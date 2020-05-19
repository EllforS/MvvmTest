package com.ellfors.mvvmtest.biz.img

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.bean.Girl
import com.ellfors.mvvmtest.databinding.ImageBinding
import com.ellfors.mvvmtest.vm.InjectUtils
import com.ellfors.mvvmtest.widget.CommonToolBar

/**
 * ImageActivity
 * 2020-05-19 11:38
 */
class MyImageActivity : BaseActivity<ImageBinding>(), CommonToolBar.CommonTopCallBack {

    private val mViewModel by lazy {
        InjectUtils.injectImageVM(this)
    }

    companion object {

        const val errorUrl =
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1589888006423&di=f0647e1131ee22f9613b460ab1ca6a34&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F01%2F54%2F12%2F0157470154cc083.jpg"

        fun start(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, MyImageActivity::class.java))
        }
    }

    override val getLayout: Int
        get() = R.layout.activity_img

    override fun initData() {
        mBinding.activity = this
        observerUI()
    }

    fun refreshImg() {
        mViewModel.refreshImage()
    }

    override fun onBackClick(view: View?) {
        finish()
    }

    override fun observerUI() {
        mViewModel.let { vm ->
            vm.mImage.observe(this, Observer {
                mBinding.girl = it
            })
            vm.mException.observe(this, Observer {
                if (it.errorCode != 100) mBinding.girl = Girl(errorUrl)
            })
        }
    }

}