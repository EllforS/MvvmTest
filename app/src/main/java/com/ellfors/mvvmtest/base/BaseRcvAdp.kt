package com.ellfors.mvvmtest.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * BaseRcvAdp
 * 2020-06-04 11:51
 */
abstract class BaseRcvAdp<T : Any>(open val mContext: AppCompatActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mDatas = mutableListOf<T>()
    var mSpanList = mutableListOf<BaseRcvSpanCount>()
    var defWeight = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return onCreate(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBind(holder, position)
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDatas[position] is BaseRcvData)
            (mDatas[position] as BaseRcvData).type
        else
            0
    }

    /* GridLayoutManager 权重设置 */
    override fun onAttachedToRecyclerView(@NonNull recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            val gridManager: GridLayoutManager? = manager
            gridManager?.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (mSpanList.size > 0 && mDatas.size > 0) {
                        for (bean in mSpanList) {
                            if (getItemViewType(position) == bean.type)
                                return bean.spanCount
                        }
                    }
                    return defWeight
                }
            }
        }
    }

    /*
    *********************************************************************************************
     */
    fun setDatas(list: MutableList<T>?) {
        if (list.isNullOrEmpty())
            return
        this.mDatas = list
        notifyDataSetChanged()
    }

    fun addDatas(list: MutableList<T>?) {
        if (list.isNullOrEmpty())
            return
        this.mDatas.addAll(list)
        notifyDataSetChanged()
    }

    fun getData2Position(pos: Int): T? {
        return if (pos >= mDatas.size)
            null
        else
            mDatas[pos]
    }

    fun setDefGridWeight(weight: Int) {
        this.defWeight = weight
    }

    fun setSpanCountList(vararg list: BaseRcvSpanCount?) {
        if (list.isNullOrEmpty())
            return
        mSpanList = Arrays.asList(*list)
    }

    fun <T : ViewDataBinding> buildBinding(parent: ViewGroup, layoutID: Int): T {
        return DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutID, parent, false)
    }

    abstract fun onCreate(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBind(holder: RecyclerView.ViewHolder, position: Int)

    class BaseViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)
}