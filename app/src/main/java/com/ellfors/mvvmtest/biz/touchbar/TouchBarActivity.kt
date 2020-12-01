package com.ellfors.mvvmtest.biz.touchbar

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.databinding.ActivityTouchBarBinding
import com.ellfors.mvvmtest.widget.CommonToolBar
import kotlinx.android.synthetic.main.activity_touch_bar.*
import kotlin.random.Random

/**
 * TouchBarActivity
 * 2020-11-26 17:27
 */
class TouchBarActivity : BaseActivity<ActivityTouchBarBinding>(), CommonToolBar.ToolBarNormalListener {

    var mValue = MutableLiveData("")
    var mTouchEnable = MutableLiveData(true)

    companion object {
        fun start(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, TouchBarActivity::class.java))
        }
    }

    override val getLayout: Int
        get() = R.layout.activity_touch_bar

    override fun initData() {
        mBinding.activity = this
    }

    override fun observerUI() {

    }

    override fun onPositiveClick(view: View?) {
        mValue.postValue(touchbar.text ?: "空")
    }

    override fun onBackClick(view: View?) {
        finish()
    }

    fun setValue() {
        val value = Random.nextInt(100)
        Log.d("MyTest", "随机值：$value")
        mBinding.touchbar.setValue(value)
    }

    fun checkEnable() {
        mTouchEnable.value = !mTouchEnable.value!!
        mBinding.touchbar.setEnable(mTouchEnable.value!!)
    }

}