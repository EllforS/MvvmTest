package com.ellfors.mvvmtest.base.rcv;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * BaseViewHolder
 * 2020-04-15 11:02
 */
public class BaseViewHolder<T extends ViewBinding> extends RecyclerView.ViewHolder {
    public T mBinding;
    private BaseRcvAdapter mAdapter;

    BaseViewHolder(@NonNull T item, @NonNull BaseRcvAdapter adp) {
        super(item.getRoot());
        this.mBinding = item;
        this.mAdapter = adp;
        setParentClickListener();
        setParentLongClickListener();
    }

    private void setParentClickListener() {
        if (mAdapter == null || mBinding == null)
            return;
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getRealPosition() != RecyclerView.NO_POSITION && mAdapter.getDatas() != null && mAdapter.getDatas().size() > 0 && mAdapter.mClickListener != null)
                    mAdapter.mClickListener.onItemClick(mBinding.getRoot(), getRealPosition(), mAdapter.getData(getRealPosition()));
            }
        });
    }

    private void setParentLongClickListener() {
        if (mAdapter == null || mBinding == null)
            return;
        mBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (getRealPosition() != RecyclerView.NO_POSITION && mAdapter.getDatas() != null && mAdapter.getDatas().size() > 0 && mAdapter.mLongClickListener != null)
                    mAdapter.mLongClickListener.onItemLongClick(mBinding.getRoot(), getRealPosition(), mAdapter.getData(getRealPosition()));
                return true;
            }
        });
    }

    /**
     * 设置公用子View的点击事件
     */
    public void setOnClickListener(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRealPosition() != RecyclerView.NO_POSITION && mAdapter.getDatas() != null && mAdapter.getDatas().size() > 0 && mAdapter.mChildClickListener != null)
                    mAdapter.mChildClickListener.onItemChildClick(v, getRealPosition(), mAdapter.getData(getRealPosition()));
            }
        });
    }

    /**
     * 设置公用子View的长按事件
     */
    public void setOnLongClickListener(View view) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (getRealPosition() != RecyclerView.NO_POSITION && mAdapter.getDatas() != null && mAdapter.getDatas().size() > 0 && mAdapter.mChildLongClickListener != null)
                    mAdapter.mChildLongClickListener.onItemChildLongClick(view, getRealPosition(), mAdapter.getData(getRealPosition()));
                return true;
            }
        });
    }

    /**
     * 获取真实的指针位置
     */
    private int getRealPosition() {
        int pos = getAdapterPosition();
        if (pos == RecyclerView.NO_POSITION)
            return getPosition();
        else
            return pos;
    }
}