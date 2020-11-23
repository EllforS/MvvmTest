package com.ellfors.mvvmtest.biz.viewtype

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseRcvAdp
import com.ellfors.mvvmtest.base.BaseRcvData
import com.ellfors.mvvmtest.base.BaseVH
import com.ellfors.mvvmtest.bean.ViewTypeContentBean
import com.ellfors.mvvmtest.bean.ViewTypeTitleBean
import com.ellfors.mvvmtest.databinding.ItemViewtypeContentBinding
import com.ellfors.mvvmtest.databinding.ItemViewtypeTitleBinding
import com.ellfors.mvvmtest.utils.ToastUtil
import java.lang.StringBuilder

/**
 * TestAdapter
 * 2020-09-08 09:39
 */
class ViewTypeAdapter(override val mContext: AppCompatActivity) : BaseRcvAdp<BaseRcvData>(mContext) {

    companion object {
        const val TYPE_TITLE = 1
        const val TYPE_CONTENT = 2
    }

    override fun onCreate(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE ->
                buildBinding<ItemViewtypeTitleBinding>(parent, R.layout.item_viewtype_title)
            else ->
                buildBinding<ItemViewtypeContentBinding>(parent, R.layout.item_viewtype_content)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBind(holder: RecyclerView.ViewHolder, bean: BaseRcvData, position: Int) {
        when (holder.itemViewType) {
            TYPE_TITLE -> {
                val mTitleHolder = holder as BaseVH<ItemViewtypeTitleBinding>
                val mTitleBean = bean.data as ViewTypeTitleBean
                mTitleHolder.binding.run {
                    titleBean = mTitleBean
                    adp = this@ViewTypeAdapter
                    executePendingBindings()
                }
            }
            TYPE_CONTENT -> {
                val mContentHolder = holder as BaseVH<ItemViewtypeContentBinding>
                val mContentBean = bean.data as ViewTypeContentBean
                mContentHolder.binding.run {
                    contentBean = mContentBean
                    executePendingBindings()
                }
            }
        }
    }

    fun onTitleClick(bean: ViewTypeTitleBean) {
        ToastUtil.showToast("${bean.title}点击...")
    }

    fun logContentData(): String {
        val builder = StringBuilder()
        for (data in mDatas) {
            if (data.data is ViewTypeContentBean) {
                val content = data.data as ViewTypeContentBean
                builder.append("${content.hint}：${content.content}\n")
            }
        }
        return builder.toString()
    }
}