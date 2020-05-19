package com.ellfors.mvvmtest.base.rcv;

import android.view.View;

/**
 * OnItemClickListener
 * 2020-04-15 11:35
 */
public interface OnItemClickListener {
    <T> void onItemClick(View view, int position, T bean);
}
