package com.ellfors.mvvmtest.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ellfors.mvvmtest.R;

/**
 * CommonToolBar
 * 2020-05-19 11:48
 */
public class CommonToolBar extends FrameLayout {

    private NormalToolBarListener mNormalListener;
    private SearchToolBarListener mSearchListener;

    public CommonToolBar(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public CommonToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonToolBar);
        int mType = typedArray.getInteger(R.styleable.CommonToolBar_toolbar_type, 1);
        switch (mType) {
            case 1:
                initNormal(context, typedArray);
                break;
            case 2:
                initSearch(context, typedArray);
                break;
        }
        typedArray.recycle();
        if (context instanceof NormalToolBarListener)
            mNormalListener = (NormalToolBarListener) context;
        if (context instanceof SearchToolBarListener)
            mSearchListener = (SearchToolBarListener) context;
    }

    private void initNormal(Context context, TypedArray typedArray) {
        View mParent = inflate(getContext(), R.layout.layout_toolbar_normal, this);
        String mTitleStr = typedArray.getString(R.styleable.CommonToolBar_toolbar_title);
        String mPositive = typedArray.getString(R.styleable.CommonToolBar_toolbar_positive);

        TextView tv_back = mParent.findViewById(R.id.tv_back);
        TextView tv_title = mParent.findViewById(R.id.tv_title);
        TextView tv_right = mParent.findViewById(R.id.tv_right);

        tv_title.setText(TextUtils.isEmpty(mTitleStr) ? "" : mTitleStr);
        tv_back.setOnClickListener(view -> {
            if (mNormalListener != null)
                mNormalListener.onBackClick(view);
            else if (context instanceof Activity)
                ((Activity) context).finish();
        });
        tv_right.setText(TextUtils.isEmpty(mPositive) ? "" : mPositive);
        tv_right.setVisibility(TextUtils.isEmpty(mPositive) ? GONE : VISIBLE);
        tv_right.setOnClickListener(view -> {
            if (mNormalListener != null)
                mNormalListener.onPositiveClick(view);
        });
    }

    private void initSearch(Context context, TypedArray typedArray) {
        View mParent = inflate(getContext(), R.layout.layout_toolbar_search, this);

        String mPositive = typedArray.getString(R.styleable.CommonToolBar_toolbar_positive);

        TextView tv_back = mParent.findViewById(R.id.tv_back);
        EditText et_search = mParent.findViewById(R.id.et_search);
        FrameLayout fl_clear_search = mParent.findViewById(R.id.fl_clear_search);
        TextView tv_right = mParent.findViewById(R.id.tv_right);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fl_clear_search.setVisibility(TextUtils.isEmpty(s.toString()) ? View.GONE : View.VISIBLE);
            }
        });
        et_search.setOnEditorActionListener((v, actionId, event) -> {
            String str = et_search.getText().toString().trim();
            if (mSearchListener != null)
                mSearchListener.onSearch(str);
            return false;
        });
        fl_clear_search.setOnClickListener(view -> {
            et_search.setText("");
            if (mSearchListener != null)
                mSearchListener.onClearSearch(view);
        });
        tv_right.setText(TextUtils.isEmpty(mPositive) ? "搜索" : mPositive);
        tv_right.setOnClickListener(view -> {
            String str = et_search.getText().toString().trim();
            if (mSearchListener != null)
                mSearchListener.onSearch(str);
        });
        tv_back.setOnClickListener(view -> {
            if (mSearchListener != null)
                mSearchListener.onBackClick(view);
            else if (context instanceof Activity)
                ((Activity) context).finish();
        });
    }

    /*
     ************************************ 外部方法与接口 ********************************
     */
    public interface NormalToolBarListener {
        void onBackClick(View view);

        void onPositiveClick(View view);
    }

    public interface SearchToolBarListener {
        void onBackClick(View view);

        void onSearch(String str);

        void onClearSearch(View view);
    }

    /**
     * Fragment无法自动绑定，需要调用Bind方法
     */
    public void bind(NormalToolBarListener listener) {
        this.mNormalListener = listener;
    }

    public void bind(SearchToolBarListener listener) {
        this.mSearchListener = listener;
    }
}
