package com.ellfors.mvvmtest.biz.list

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseRcvAdp
import com.ellfors.mvvmtest.base.BaseVH
import com.ellfors.mvvmtest.bean.ArticlesBean
import com.ellfors.mvvmtest.databinding.ArticleItemBinding

/**
 * MyArticleAdapter
 * 2020-05-19 18:12
 */
class MyArticleAdapter(override val mContext: AppCompatActivity) : BaseRcvAdp<ArticlesBean>(mContext) {

    override fun onCreate(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return buildBinding<ArticleItemBinding>(parent, R.layout.item_article)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBind(holder: RecyclerView.ViewHolder, bean: ArticlesBean, position: Int) {
        val mHolder = holder as BaseVH<ArticleItemBinding>
        mHolder.binding.run {
            lifecycleOwner = mContext
            adp = this@MyArticleAdapter
            article = bean
            executePendingBindings()//解决databinding闪烁问题
        }
    }

    fun toDetail(url: String?) {
        MyArticleDetActivity.start(mContext, url)
    }

}