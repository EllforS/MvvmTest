package com.ellfors.mvvmtest.base.rcv;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseRcvAdapter
 * 2020-04-13 17:32
 */
public abstract class BaseRcvAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context mContext;
    private List<T> mDatas;
    protected OnItemClickListener mClickListener;
    protected OnItemLongClickListener mLongClickListener;
    protected OnItemChildClickListener mChildClickListener;
    protected OnItemChildLongClickListener mChildLongClickListener;

    public BaseRcvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder<>(onCreate(parent, viewType), this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseViewHolder)
            onBindView((BaseViewHolder) holder, mDatas == null || mDatas.size() <= position ? null : mDatas.get(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() > position && mDatas.get(position) instanceof BaseRcvData)
            return ((BaseRcvData) mDatas.get(position)).getType();
        else
            return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    /*
     ********************************************************************************************
     */
    public abstract ViewBinding onCreate(ViewGroup parent, int viewType);

    public abstract void onBindView(BaseViewHolder holder, T item, int position);

    /*
     ********************************************************************************************
     */
    public void setDatas(List<T> data) {
        mDatas = data;
        notifyDataSetChanged();
    }

    public void addDatas(List<T> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public T getData(int position) {
        return mDatas == null || mDatas.size() <= position ? null : mDatas.get(position);
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public <K> List<BaseRcvData> buildRcvData(List<K> data, int type) {
        List<BaseRcvData> datas = new ArrayList<>();
        if (data != null && data.size() > 0) {
            for (K bean : data) {
                datas.add(new BaseRcvData(bean, type));
            }
        }
        return datas;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mLongClickListener = listener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        this.mChildClickListener = listener;
    }

    public void setOnItemChildLongClickListener(OnItemChildLongClickListener listener) {
        this.mChildLongClickListener = listener;
    }

}
