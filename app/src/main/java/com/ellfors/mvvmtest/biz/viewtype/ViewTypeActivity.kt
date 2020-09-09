package com.ellfors.mvvmtest.biz.viewtype

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.base.BaseRcvData
import com.ellfors.mvvmtest.databinding.ActivityViewTypeBinding
import com.ellfors.mvvmtest.widget.CommonToolBar

/**
 * TestActivity
 * 2020-09-08 09:36
 */
class ViewTypeActivity : BaseActivity<ActivityViewTypeBinding>(), CommonToolBar.ToolBarNormalListener {

    val mAdapter by lazy {
        ViewTypeAdapter(this)
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, ViewTypeActivity::class.java))
        }
    }

    override val getLayout: Int
        get() = R.layout.activity_view_type

    override fun initData() {
        mBinding.rcvViewType.run {
            layoutManager = LinearLayoutManager(this@ViewTypeActivity)
            adapter = mAdapter
        }
        mAdapter.setDatas(getTestData())
    }

    override fun observerUI() {

    }

    fun getTestData(): MutableList<BaseRcvData> {
        val list = mutableListOf<BaseRcvData>()
        for (i in 0..5) {
            if (i == 0)
                list.add(BaseRcvData(ViewTypeTitleBean("标题$i"), ViewTypeAdapter.TYPE_TITLE))
            else
                list.add(BaseRcvData(ViewTypeContentBean("请输入内容$i", ""), ViewTypeAdapter.TYPE_CONTENT))
        }
        return list
    }

    override fun onPositiveClick(view: View?) {
        mBinding.logData = mAdapter.logContentData()
    }

    override fun onBackClick(view: View?) {
        finish()
    }
}