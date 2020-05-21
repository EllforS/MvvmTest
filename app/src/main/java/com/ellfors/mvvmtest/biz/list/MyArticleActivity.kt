package com.ellfors.mvvmtest.biz.list

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.bean.ArticlesBean
import com.ellfors.mvvmtest.databinding.ArticleBinding
import com.ellfors.mvvmtest.vm.InjectUtils
import com.ellfors.mvvmtest.vm.viewmodel.ArticleVM
import com.ellfors.mvvmtest.widget.CommonToolBar

/**
 * ListActivity
 * 2020-05-19 12:19
 */
class MyArticleActivity : BaseActivity<ArticleBinding>(), CommonToolBar.CommonTopCallBack {

    val mViewModel: ArticleVM by lazy {
        InjectUtils.injectArticleVM(this)
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
        mBinding.activity = this
        mBinding.viewmodel = mViewModel

        mBinding.rcvList.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = mAdapter
        }

        mBinding.refreshLayout.autoRefresh()
    }

    override fun onBackClick(view: View?) {
        finish()
    }

    override fun observerUI() {
        mViewModel.let { vm ->
            vm.mArticles.observe(this, Observer {
                if (vm.mPage == 1)
                    mAdapter.setDatas(it)
                else
                    mAdapter.addDatas(it)
            })
        }
    }
}