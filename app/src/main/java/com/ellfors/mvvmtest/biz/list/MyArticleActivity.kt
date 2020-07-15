package com.ellfors.mvvmtest.biz.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.databinding.ArticleBinding
import com.ellfors.mvvmtest.vm.VMInjectUtil
import com.ellfors.mvvmtest.vm.viewmodel.ArticleVM

/**
 * ListActivity
 * 2020-05-19 12:19
 */
class MyArticleActivity : BaseActivity<ArticleBinding>() {

    val mViewModel: ArticleVM by lazy {
        VMInjectUtil.injectArticleVM(this)
    }

    val mAdapter by lazy {
        MyArticleAdapter(this)
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, MyArticleActivity::class.java))
        }
    }

    override val getLayout: Int
        get() = R.layout.activity_article

    override fun initData() {
        setTargetView(mBinding.rcvList)
        mBinding.activity = this
        mBinding.viewmodel = mViewModel

        mBinding.rcvList.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = mAdapter
        }

        mBinding.refreshLayout.autoRefresh()
    }

    override fun observerUI() {
        mViewModel.let { vm ->
            vm.mArticles.observe(this, Observer {
                if (vm.mPage == 1)
                    mAdapter.setDatas(it)
                else
                    mAdapter.addDatas(it)
            })
            vm.mException.observe(this, Observer {
                if (it.errorCode == -1)
                    showNetError()
                else
                    showContent()
            })
        }
    }
}