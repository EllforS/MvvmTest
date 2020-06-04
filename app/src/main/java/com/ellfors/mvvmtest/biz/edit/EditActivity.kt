package com.ellfors.mvvmtest.biz.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.databinding.EditBinding
import com.ellfors.mvvmtest.vm.VMInjectUtil
import com.ellfors.mvvmtest.vm.viewmodel.EditVM

/**
 * EditActivity
 * 2020-05-25 11:58
 */
class EditActivity : BaseActivity<EditBinding>() {

    val mViewModel: EditVM by lazy {
        VMInjectUtil.injectEditVM(this)
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, EditActivity::class.java))
        }
    }

    override val getLayout: Int
        get() = R.layout.activity_edit

    override fun initData() {
        mBinding.vm = mViewModel
    }

    override fun observerUI() {

    }
}