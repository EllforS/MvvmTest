package com.ellfors.mvvmtest.biz.list

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseActivity
import com.ellfors.mvvmtest.databinding.ArticleBinding
import com.ellfors.mvvmtest.vm.InjectUtils
import com.ellfors.mvvmtest.widget.CommonToolBar

/**
 * ListActivity
 * 2020-05-19 12:19
 */
class MyArticleActivity : BaseActivity<ArticleBinding>(), CommonToolBar.CommonTopCallBack {

    val mViewModel by lazy {
        InjectUtils.injectArticleVM(this)
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
    }

    fun get() {
        mViewModel.getArticles()
    }

    override fun onBackClick(view: View?) {
        finish()
    }

    override fun observerUI() {
        mViewModel.let { vm ->
            vm.mArticles.observe(this, Observer {

            })
            vm.mException.observe(this, Observer {

            })
        }
    }
}