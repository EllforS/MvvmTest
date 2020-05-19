package com.ellfors.mvvmtest.base.rcv;

import android.view.View;

/**
 * OnItemChildLongClickListener
 * 2020-04-15 11:36
 */
public interface OnItemChildLongClickListener {
    <T> void onItemChildLongClick(View view, int position, T bean);
}
