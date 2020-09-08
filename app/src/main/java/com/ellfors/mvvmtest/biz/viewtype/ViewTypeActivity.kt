package com.ellfors.mvvmtest.biz.viewtype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.base.BaseRcvData
import com.ellfors.mvvmtest.databinding.ActivityViewTypeBinding

/**
 * TestActivity
 * 2020-09-08 09:36
 */
class ViewTypeActivity : BaseActivity<ActivityViewTypeBinding>() {

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
        for (i in 1..10) {
            if (i == 1 || i % 3 == 0)
                list.add(BaseRcvData(ViewTypeTitleBean("标题$i"), ViewTypeAdapter.TYPE_TITLE))
            else
                list.add(BaseRcvData(ViewTypeContentBean("内容$i"), ViewTypeAdapter.TYPE_CONTENT))
        }
        return list
    }
}