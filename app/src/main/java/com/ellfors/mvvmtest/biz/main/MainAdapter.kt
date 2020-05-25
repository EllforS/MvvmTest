package com.ellfors.mvvmtest.biz.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.biz.edit.EditActivity
import com.ellfors.mvvmtest.biz.img.MyImageActivity
import com.ellfors.mvvmtest.biz.list.MyArticleActivity
import com.ellfors.mvvmtest.biz.time.TimeDownActivity
import com.ellfors.mvvmtest.databinding.ItemMainBinding

/**
 * MainAdapter
 * 2020-05-25 11:30
 */
class MainAdapter(val mContext: MainActivity) : RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {

    var mDatas = mutableListOf<String>()

    init {
        mDatas.add("图片")
        mDatas.add("列表")
        mDatas.add("倒计时")
        mDatas.add("输入")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_main, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.let {
            it.binding.let { binding ->
                binding.name = mDatas[position]
                binding.adapter = this
                binding.executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    fun jump(type: String) {
        when (type) {
            mDatas[0] ->
                MyImageActivity.start(mContext)
            mDatas[1] ->
                MyArticleActivity.start(mContext)
            mDatas[2] ->
                TimeDownActivity.start(mContext)
            mDatas[3] ->
                EditActivity.start(mContext)
        }
    }

    class ItemViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)
}