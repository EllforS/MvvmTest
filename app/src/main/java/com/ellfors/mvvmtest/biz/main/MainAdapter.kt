package com.ellfors.mvvmtest.biz.main

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseRcvAdp
import com.ellfors.mvvmtest.base.BaseVH
import com.ellfors.mvvmtest.biz.edit.EditActivity
import com.ellfors.mvvmtest.biz.img.MyImageActivity
import com.ellfors.mvvmtest.biz.list.MyArticleActivity
import com.ellfors.mvvmtest.biz.mmkv.MMKVActivity
import com.ellfors.mvvmtest.biz.time.TimeDownActivity
import com.ellfors.mvvmtest.biz.touchbar.TouchBarActivity
import com.ellfors.mvvmtest.biz.viewtype.ViewTypeActivity
import com.ellfors.mvvmtest.databinding.ItemMainBinding
import com.ellfors.mvvmtest.utils.NotificationUtil
import com.ellfors.mvvmtest.utils.ToastUtil

/**
 * MainAdapter
 * 2020-05-25 11:30
 */
class MainAdapter(override val mContext: AppCompatActivity) : BaseRcvAdp<String>(mContext) {

    init {
        mDatas.add("图片")
        mDatas.add("列表")
        mDatas.add("倒计时")
        mDatas.add("输入")
        mDatas.add("ItemViewType")
        mDatas.add("检查通知权限")
        mDatas.add("进度条")
        mDatas.add("MMKV")

        setDatas(mDatas)
    }

    override fun onCreate(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return buildBinding<ItemMainBinding>(parent, R.layout.item_main)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBind(holder: RecyclerView.ViewHolder, bean: String, position: Int) {
        val mHolder = holder as BaseVH<ItemMainBinding>
        mHolder.binding.run {
            name = bean
            adapter = this@MainAdapter
            executePendingBindings()
        }
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
            mDatas[4] ->
                ViewTypeActivity.start(mContext)
            mDatas[5] -> {
                if (NotificationUtil.requsetNotificationPermission(mContext))
                    ToastUtil.showToast("已开启通知权限")
            }
            mDatas[6] ->
                TouchBarActivity.start(mContext)
            mDatas[7] ->
                MMKVActivity.start(mContext)
        }
    }
}