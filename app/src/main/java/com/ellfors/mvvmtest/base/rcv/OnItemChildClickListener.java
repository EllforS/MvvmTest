package com.ellfors.mvvmtest.base.rcv;

import android.view.View;

/**
 * OnItemChildClickListener
 * 2020-04-15 11:35
 */
public interface OnItemChildClickListener {
    <T> void onItemChildClick(View view, int position, T bean);
}
