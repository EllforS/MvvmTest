package com.ellfors.mvvmtest.base.rcv;

import android.view.View;

/**
 * OnItemLongChildListener
 * 2020-04-15 11:40
 */
public interface OnItemLongClickListener {
    <T> void onItemLongClick(View view, int position, T bean);
}
