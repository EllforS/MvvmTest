package com.ellfors.mvvmtest.biz.viewtype

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ellfors.mvvmtest.R
import com.ellfors.mvvmtest.base.BaseRcvAdp
import com.ellfors.mvvmtest.base.BaseRcvData
import com.ellfors.mvvmtest.base.BaseViewHolder
import com.ellfors.mvvmtest.databinding.ItemViewtypeContentBinding
import com.ellfors.mvvmtest.databinding.ItemViewtypeTitleBinding
import com.ellfors.mvvmtest.utils.TsUtil
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
            TYPE_TITLE -> BaseViewHolder<ItemViewtypeTitleBinding>(buildBinding(parent, R.layout.item_viewtype_title))
            TYPE_CONTENT -> BaseViewHolder<ItemViewtypeContentBinding>(buildBinding(parent, R.layout.item_viewtype_content))
            else -> BaseViewHolder<ItemViewtypeContentBinding>(buildBinding(parent, R.layout.item_viewtype_content))
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_TITLE -> {
                val mTitleHolder = holder as BaseViewHolder<ItemViewtypeTitleBinding>
                val mTitleBean = mDatas[position].data as ViewTypeTitleBean
                mTitleHolder.binding.run {
                    titleBean = mTitleBean
                    adp = this@ViewTypeAdapter
                }
            }
            TYPE_CONTENT -> {
                val mContentHolder = holder as BaseViewHolder<ItemViewtypeContentBinding>
                val mContentBean = mDatas[position].data as ViewTypeContentBean
                mContentHolder.binding.run {
                    contentBean = mContentBean
                }
            }
        }
    }

    fun onTitleClick(bean: ViewTypeTitleBean) {
        TsUtil.showToast("${bean.title}点击...")
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