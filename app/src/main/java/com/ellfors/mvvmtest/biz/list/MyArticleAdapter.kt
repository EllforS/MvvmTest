package com.ellfors.mvvmtest.biz.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.bean.ArticlesBean
import com.ellfors.mvvmtest.databinding.ArticleItemBinding

/**
 * MyArticleAdapter
 * 2020-05-19 18:12
 */
class MyArticleAdapter(val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mDatas = mutableListOf<ArticlesBean>()

    fun setDatas(list: MutableList<ArticlesBean>) {
        this.mDatas = list
        notifyDataSetChanged()
    }

    fun addDatas(list: MutableList<ArticlesBean>) {
        this.mDatas.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_article, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.binding.let {
                    it.article = mDatas[position]
                    it.executePendingBindings()//解决databinding闪烁问题
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    class ItemViewHolder(val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root)
}