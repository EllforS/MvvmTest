package com.ellfors.mvvmtest.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ellfors.mvvmtest.R;
import com.ellfors.mvvmtest.utils.DensityUtil;

/**
 * CommonToolBar
 * 2020-05-19 11:48
 */
public class CommonToolBar extends FrameLayout {

    private static final Integer HEIGHT = 48;
    private String mTitleStr;

    private CommonTopCallBack mCallBack;

    public CommonToolBar(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public CommonToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mParentHeight = MeasureSpec.getSize(heightMeasureSpec);
        int mParentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY)
            width = mParentWidth;
        else
            width = DensityUtil.INSTANCE.dp2px(360);
        if (heightMode == MeasureSpec.EXACTLY)
            height = mParentHeight;
        else
            height = DensityUtil.INSTANCE.dp2px(HEIGHT);
        setMeasuredDimension(width, height);
        setBackgroundColor(Color.WHITE);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonToolBar);
        mTitleStr = typedArray.getString(R.styleable.CommonToolBar_title);
        typedArray.recycle();

        buildBackView(context);
        buildTitleView(context);
        buildBottomLine(context);
        if (context instanceof CommonTopCallBack)
            mCallBack = (CommonTopCallBack) context;
    }

    private void buildBackView(Context context) {
        TextView back = new TextView(context);
        FrameLayout.LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                DensityUtil.INSTANCE.dp2px(HEIGHT)
        );
        params.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
        back.setLayoutParams(params);
        back.setPadding(DensityUtil.INSTANCE.dp2px(10), 0, DensityUtil.INSTANCE.dp2px(10), 0);
        back.setText("返回");
        back.getPaint().setTextSize(DensityUtil.INSTANCE.sp2px(14));
        back.setTextColor(context.getResources().getColor(R.color.color_333333));
        back.setGravity(Gravity.CENTER);
        back.setOnClickListener(view -> {
            if (mCallBack != null)
                mCallBack.onBackClick(view);
            else if (context instanceof Activity)
                ((Activity) context).finish();
        });
        addView(back);
    }

    private void buildTitleView(Context context) {
        TextView title = new TextView(context);
        FrameLayout.LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                DensityUtil.INSTANCE.dp2px(HEIGHT)
        );
        params.gravity = Gravity.CENTER;
        title.setLayoutParams(params);
        title.setText(TextUtils.isEmpty(mTitleStr) ? "" : mTitleStr);
        title.getPaint().setTextSize(DensityUtil.INSTANCE.sp2px(16));
        title.setTextColor(context.getResources().getColor(R.color.color_333333));
        title.setGravity(Gravity.CENTER);
        addView(title);
    }

    private void buildBottomLine(Context context) {
        View view = new View(context);
        FrameLayout.LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                1
        );
        params.gravity = Gravity.BOTTOM;
        view.setLayoutParams(params);
        view.setBackgroundColor(context.getResources().getColor(R.color.tra_black_10));
        addView(view);
    }

    /*
     ************************************ 外部方法与接口 ********************************
     */
    public interface CommonTopCallBack {
        void onBackClick(View view);
    }

    /**
     * Fragment无法自动绑定，需要调用Bind方法
     */
    public void bind(CommonTopCallBack back) {
        this.mCallBack = back;
    }
}
